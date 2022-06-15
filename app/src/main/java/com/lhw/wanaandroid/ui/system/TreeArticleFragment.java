package com.lhw.wanaandroid.ui.system;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.WebViewActivity;
import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.ui.base.BaseFragment;
import com.lhw.wanaandroid.ui.system.adapter.TreeArticlListeAdapter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class TreeArticleFragment extends BaseFragment implements SystemArticleContract.ISystemArticleView ,TreeArticlListeAdapter.OnItemClickListener{
    private RefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private List<ArticleDetail> articleDetails;
    private int page = 0;
    int id;
    SystemArticlePresenter systemArticlePresenter;
    TreeArticlListeAdapter baseArticleAdapter;

    public TreeArticleFragment(int id) {
        this.id =id;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_base_article;
    }

    @Override
    protected void initSubViews() {
        refreshLayout = (RefreshLayout)find(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this.getActivity()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this.getActivity()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                articleDetails.clear();
                int pag1 = 0;
                systemArticlePresenter.getSystemArticleData(pag1,id);
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page +=1;
                systemArticlePresenter.getSystemArticleData(page,id);
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });

        recyclerView = find(R.id.tree_article_recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        articleDetails = new ArrayList<>();
        recyclerView.setLayoutManager(linearLayoutManager);
        baseArticleAdapter = new TreeArticlListeAdapter(recyclerView,getContext(), articleDetails);
        baseArticleAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(baseArticleAdapter);

        systemArticlePresenter = new SystemArticlePresenter(this);
        systemArticlePresenter.getSystemArticleData(page,id);
    }

    public static TreeArticleFragment newInstance(int id) {//int cid
        Bundle args = new Bundle();
        TreeArticleFragment fragment = new TreeArticleFragment(id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void getSystemArticleSuccess(List<ArticleDetail> datas) {

        baseArticleAdapter.setArticle(datas);
    }

    @Override
    public void getFailure(Throwable throwable) {
        Toast.makeText(getContext(), "遇到了问题~~~~~", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(ArticleDetail data) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra(WebViewActivity.WEB_URL,data.getLink());
        startActivity(intent);
    }
}
