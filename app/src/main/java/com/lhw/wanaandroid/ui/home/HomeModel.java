package com.lhw.wanaandroid.ui.home;

import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BannerData;
import com.lhw.wanaandroid.bean.BaseResponse;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.network.WanAndroidService;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 *
 * 功能描述：从网络获取数据
 */
public class HomeModel implements HomeContract.IHomeModel {
    //model层操作获取数据
    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return RetrofitClient.getInstance().getService(WanAndroidService.class)
                .getBanner();
    }
    //model层操作获取数据
    @Override
    public Observable<BaseResponse<Articles>> getArticleData(int page) {
        return RetrofitClient.getInstance().getService(WanAndroidService.class)
                .getArticleData(page);
    }

    @Override
    public Observable<BaseResponse<Articles>> getHomeTopList() {
        return  RetrofitClient.getInstance().getService(WanAndroidService.class)
                .getHomeTopList();
    }

    @Override
    public Observable<BaseResponse<Articles>> collect(int id ) {
        return RetrofitClient.getInstance().getService(WanAndroidService.class)
                .collectArticle(id);
    }

    @Override
    public Observable<BaseResponse<Articles>> uncollect(int id ) {
        return  RetrofitClient.getInstance().getService(WanAndroidService.class)
                .unCollectArticle(id);
    }


}

