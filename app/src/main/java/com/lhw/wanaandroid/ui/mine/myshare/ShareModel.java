package com.lhw.wanaandroid.ui.mine.myshare;

import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BaseResponse;
import com.lhw.wanaandroid.bean.ShareData;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.network.WanAndroidService;

import io.reactivex.rxjava3.core.Observable;

public class ShareModel implements ShareArticleSelectContract.IModel{

    @Override
    public Observable<BaseResponse<ShareData>> getShareArticleList(int page) {
        return  RetrofitClient.getInstance()
                .getService(WanAndroidService.class).shareArticleList(page);
    }

}
