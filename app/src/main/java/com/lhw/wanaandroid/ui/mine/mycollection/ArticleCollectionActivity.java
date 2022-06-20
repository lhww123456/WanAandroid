package com.lhw.wanaandroid.ui.mine.mycollection;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.WebViewActivity;
import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.ui.base.BaseActivity;
import com.lhw.wanaandroid.ui.mine.mycollection.adapter.CollectRecyclerViewAdapter;
import com.lhw.wanaandroid.ui.mine.myshare.SharePresenter;
import com.lhw.wanaandroid.ui.mine.myshare.adapter.MyShareListAdapter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class ArticleCollectionActivity extends BaseActivity implements CollectRecyclerViewAdapter.OnItemClickListener, CollectContract.IView {

    private CollectPresenter collectPresenter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private int page = 0;//从第1也开始
    private RefreshLayout refreshLayout;
    private ImageView backfinish,add;
    List<ArticleDetail> dataList;
    private CollectRecyclerViewAdapter collectRecyclerViewAdapter;
    @Override
    protected void initSubViews() {
        refreshLayout = (RefreshLayout) find(R.id.collect_refreshLayout);
        this.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                dataList.clear();
                int page1 = 0;
                collectPresenter.getCollectArticlePage(page1);
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        this.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page += 1;
                collectPresenter.getCollectArticlePage(page);
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });
        recyclerView = find(R.id.collect_recycleView);
        backfinish = find(R.id.back_finish_collect);
        backfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dataList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        collectRecyclerViewAdapter = new CollectRecyclerViewAdapter(recyclerView, this, dataList);
        collectRecyclerViewAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(collectRecyclerViewAdapter);

        collectPresenter = new CollectPresenter(this);
        collectPresenter.getCollectArticlePage(page);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collectlist;
    }

    @Override
    public void onItemClick(ArticleDetail data) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.WEB_URL,data.getLink());
        intent.putExtra("titile",data.getTitle());
        startActivity(intent);
    }

    @Override
    public void getCollectArticleSuccess(Articles articles) {
        collectRecyclerViewAdapter.setArticleData(articles.getDatas());
    }

    @Override
    public void getCollectArticleFailure(Throwable throwable) {
        Toast.makeText(this, "遇到了问题~~~~~",Toast.LENGTH_SHORT).show();
    }
}