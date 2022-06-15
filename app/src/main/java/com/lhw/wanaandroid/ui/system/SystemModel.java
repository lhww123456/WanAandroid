package com.lhw.wanaandroid.ui.system;

import com.lhw.wanaandroid.bean.BaseBean;
import com.lhw.wanaandroid.bean.TreeData;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.network.WanAndroidService;


import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 *
 * 功能描述：从网络获取数据
 */
public class SystemModel implements SystemContract.ISystemModel {
    @Override
    public Observable<BaseBean<List<TreeData>>> getSystemData() {
        return RetrofitClient.getInstance().getService(WanAndroidService.class)
                .getTree();
    }
    //model层操作获取数据
}

