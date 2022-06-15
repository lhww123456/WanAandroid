package com.lhw.wanaandroid.ui.nav;


import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.bean.NavCategoryBean;
import com.lhw.wanaandroid.ui.base.BaseFragment;
import com.lhw.wanaandroid.ui.nav.adapter.NavRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;
/*
补充：layoutManager.setFlexDirection(FlexDirection.ROW) ;//设置水平方向。从左到右
        FlexDirection.COLUMN //垂直方向，从上到下
        FlexDirection.ROW_REVERSE //水平方向，从右到左
        FlexDirection.COLUMN_REVERSE //垂直方向，从下到上

        layoutManager.setFlexWrap(FlexWrap.WRAP); //是否换行FlexWrap.NOWRAP不换行 FlexWrap.WRAP换行
        layoutManager.setJustifyContent(JustifyContent.FLEX_END); //项目在主轴上对齐方式，例如上面定义水平方向，就是水平方向对齐方式
        layoutManager.setAlignItems(AlignItems.CENTER);//项目在交叉口对齐方式，因为上面定义水平方向，则为在垂直方向对齐方式
*/


public class NavFragment extends BaseFragment implements NavContract.INavView {
    private NavRecycleViewAdapter mAdapter;
    private RecyclerView recyclerView;
    private NavPresenter navPresenter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_nav;
    }

    @Override
    protected void initSubViews() {
        navPresenter = new NavPresenter(this);
        navPresenter.getNavData();

        recyclerView =find(R.id.nav_rv);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<NavCategoryBean> list_data = new ArrayList<>();
        mAdapter = new NavRecycleViewAdapter(getActivity(),list_data);

        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void getNavSuccess(List<NavCategoryBean> datas) {
        mAdapter.setNavData(datas);
    }

    @Override
    public void getFailure(Throwable throwable) {
        Toast.makeText(getActivity(),"出错了~~~~~~",Toast.LENGTH_SHORT).show();
    }
}