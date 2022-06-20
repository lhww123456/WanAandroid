package com.lhw.wanaandroid.ui.system.detail;

import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BaseResponse;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.network.WanAndroidService;


import io.reactivex.rxjava3.core.Observable;

/**
 *
 * 功能描述：从网络获取数据
 */
public class SystemArticleModel implements SystemArticleContract.ISystemArticleModel {
    @Override
    public Observable<BaseResponse<Articles>> getArticleData(int page, int cid) {
        return RetrofitClient.getInstance().getService(WanAndroidService.class)
                .getTreeList(page,cid);
    }
    //model层操作获取数据


}

