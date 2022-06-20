package com.lhw.wanaandroid.ui.nav;

import com.lhw.wanaandroid.bean.BaseResponse;
import com.lhw.wanaandroid.bean.NavCategoryBean;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.network.WanAndroidService;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 *
 * 功能描述：从网络获取数据
 */
public class NavModel implements NavContract.INavModel {

    @Override
    public Observable<BaseResponse<List<NavCategoryBean>>> getNavData() {
        return RetrofitClient.getInstance().getService(WanAndroidService.class)
                .getNavCategoryData();
    }
}

