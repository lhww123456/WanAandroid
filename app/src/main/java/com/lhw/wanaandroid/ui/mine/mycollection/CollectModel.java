package com.lhw.wanaandroid.ui.mine.mycollection;

import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BaseResponse;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.network.WanAndroidService;

import io.reactivex.rxjava3.core.Observable;

public class CollectModel implements CollectContract.IModel{

    @Override
    public Observable<BaseResponse<Articles>> getCollectArticleList(int page) {
        return RetrofitClient.getInstance()
                .getService(WanAndroidService.class).getCollectArticleList(page);
    }
}
