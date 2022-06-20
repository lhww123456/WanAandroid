package com.lhw.wanaandroid.ui.system.detail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.WebViewActivity;
import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.ui.base.BaseFragment;
import com.lhw.wanaandroid.ui.system.detail.adapter.SystemArticlListeAdapter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class SystemArticleFragment extends BaseFragment implements SystemArticleContract.ISystemArticleView, SystemArticlListeAdapter.OnItemClickListener{
    private RefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private List<ArticleDetail> articleDetails;
    private int page = 0;
    int id;
    private  ProgressDialog progressDialog;

    SystemArticlePresenter systemArticlePresenter;
    SystemArticlListeAdapter baseArticleAdapter;

    public SystemArticleFragment(int id) {
        this.id =id;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_base_article;
    }

    @Override
    protected void initSubViews() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("正在努力为您加载···");
        progressDialog.show();
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
        baseArticleAdapter = new SystemArticlListeAdapter(recyclerView,getContext(), articleDetails);
        baseArticleAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(baseArticleAdapter);

        systemArticlePresenter = new SystemArticlePresenter(this);
        systemArticlePresenter.getSystemArticleData(page,id);
    }

    public static SystemArticleFragment newInstance(int cid) {//int cid
        Bundle args = new Bundle();
        SystemArticleFragment fragment = new SystemArticleFragment(cid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void getSystemArticleSuccess(List<ArticleDetail> datas) {
        progressDialog.dismiss();
        baseArticleAdapter.setArticle(datas);
    }

    @Override
    public void getFailure(Throwable throwable) {
        progressDialog.dismiss();
        Toast.makeText(getContext(), "遇到了问题~~~~~", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(ArticleDetail data) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra(WebViewActivity.WEB_URL,data.getLink());
        startActivity(intent);
    }
}
