package com.lhw.wanaandroid.ui.home;


import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.WebViewActivity;
import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.bean.BannerData;
import com.lhw.wanaandroid.ui.base.BaseFragment;
import com.lhw.wanaandroid.ui.home.adapter.HomeRecycleViewAdapter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;


/**
 * 首页文章
 */

public class HomeFragment extends BaseFragment implements HomeContract.IHomeView , HomeRecycleViewAdapter.OnItemClickListener {
    private HomePresenter homePresenter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Banner banner;
    private RefreshLayout refreshLayout;
    private HomeRecycleViewAdapter homeRecycleViewAdapter;
    private int page = 0;
    private List<ArticleDetail> dataList;
    private List<BannerData> bannerData;
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initSubViews() {
        refreshLayout = (RefreshLayout)find(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this.getActivity()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this.getActivity()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                dataList.clear();
                int page1 = 0;
                homePresenter.getAarticleData(page1);
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page += 1;
                homePresenter.getAarticleData(page);
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });

        recyclerView =  find(R.id.home_recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        banner = find(R.id.banner);

        dataList = new ArrayList<>();
        bannerData = new ArrayList<>();

        recyclerView.setLayoutManager(linearLayoutManager);

        homeRecycleViewAdapter = new HomeRecycleViewAdapter(recyclerView,getContext(), dataList, bannerData);
        homeRecycleViewAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(homeRecycleViewAdapter);

        homePresenter = new HomePresenter(this);
        homePresenter.getBannerData();
        homePresenter.getAarticleData(page);
    }


    @Override
    public void onItemClick(ArticleDetail datas) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra(WebViewActivity.WEB_URL,datas.getLink());
        intent.putExtra("titile",datas.getTitle());
        startActivity(intent);
    }

    @Override //TODO 暂未实现
    public void onCollectionClick(View view, int position) {

    }

    @Override
    public void getBannerSuccess(List<BannerData> banners) {

        homeRecycleViewAdapter.setBannerData(banners);
    }

    @Override
    public void getArticleSuccess(List<ArticleDetail> datas) {
        homeRecycleViewAdapter.setArticleData(datas);
    }

    @Override
    public void getFailure(Throwable throwable) {
        Toast.makeText(getContext(), "遇到了问题~~~~~",Toast.LENGTH_SHORT).show();
    }
}