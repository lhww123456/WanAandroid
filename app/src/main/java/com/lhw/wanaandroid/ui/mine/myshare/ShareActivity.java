package com.lhw.wanaandroid.ui.mine.myshare;


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
import com.lhw.wanaandroid.bean.ShareData;
import com.lhw.wanaandroid.ui.base.BaseActivity;
import com.lhw.wanaandroid.ui.mine.coin.CoinActivity;
import com.lhw.wanaandroid.ui.mine.myshare.adapter.MyShareListAdapter;
import com.lhw.wanaandroid.ui.mine.myshare.add.ShareAddActivity;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class ShareActivity extends BaseActivity implements ShareArticleSelectContract.IView,MyShareListAdapter.OnItemClickListener {
    private SharePresenter sharePresenter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private int page = 1;//从第1也开始
    private RefreshLayout refreshLayout;
    private ImageView backfinish,add;
    List<ArticleDetail> dataList;
    private MyShareListAdapter shareArtRecyclerViewAdapter;
    @Override
    protected void initSubViews() {
        refreshLayout = (RefreshLayout)find(R.id.share_refreshLayout);
        this.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                dataList.clear();
                int page1 = 0;
                sharePresenter.getShareArticle(page1);
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        this.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page += 1;
                sharePresenter.getShareArticle(page);
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });
        recyclerView =find(R.id.share_recycleView);
        backfinish = find(R.id.back_finish_mine);
        backfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        add = find(R.id.share_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(ShareActivity.this, ShareAddActivity.class);
                startActivity(intent3);
            }
        });


        dataList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        shareArtRecyclerViewAdapter = new MyShareListAdapter(recyclerView, this, dataList);
        shareArtRecyclerViewAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(shareArtRecyclerViewAdapter);

        sharePresenter = new SharePresenter(this);
        sharePresenter.getShareArticle(page);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share;
    }

    @Override
    public void getShareArticleSuccess(ShareData articleDetail) {
        shareArtRecyclerViewAdapter.setShareArticleData(articleDetail.getShareArticles().getDatas());
    }

    @Override
    public void getShareArticleFailure(Throwable throwable) {
        Toast.makeText(this, "遇到了问题~~~~~",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemClick(ArticleDetail datas) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.WEB_URL,datas.getLink());
        intent.putExtra("titile",datas.getTitle());
        startActivity(intent);
    }
}