package com.lhw.wanaandroid.ui.system;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.bean.Children;
import com.lhw.wanaandroid.bean.TreeData;
import com.lhw.wanaandroid.ui.base.BaseFragment;
import com.lhw.wanaandroid.ui.system.adapter.SystemRecycleViewAdapter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class SystemFragment extends BaseFragment implements SystemContract.ISystemView, SystemRecycleViewAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private SystemPresenter systemPresenter;
    private List<TreeData> dataBeans;

    SystemRecycleViewAdapter systemRecycleViewAdapter;
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_system;
    }

    @Override
    protected void initSubViews() {
        refreshLayout = (RefreshLayout)find(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this.getActivity()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this.getActivity()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                systemPresenter.getSystemData();
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                systemPresenter.getSystemData();
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });



        recyclerView = find(R.id.tree_recycleView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        dataBeans = new ArrayList<>();

        systemRecycleViewAdapter = new SystemRecycleViewAdapter(recyclerView,getContext(), dataBeans);
        systemRecycleViewAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(systemRecycleViewAdapter);

        systemPresenter = new SystemPresenter(this);
        systemPresenter.getSystemData();
        
    }


    @Override
    public void getSystemSuccess(List<TreeData> data) {
        systemRecycleViewAdapter.setSystemData(data);
    }

    @Override
    public void getFailure(Throwable throwable) {

    }

    @Override
    public void onItemClick(TreeData mTree,int position) {
        Intent intent = new Intent(getActivity(), SystemArticleActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("mtree", mTree);
        intent.putExtras(b);
        startActivity(intent);
    }

}
