package com.lhw.wanaandroid.ui.mine.myshare;

import android.util.Log;

import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BaseResponse;
import com.lhw.wanaandroid.bean.ShareData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SharePresenter implements ShareArticleSelectContract.IPresenter{

    private ShareArticleSelectContract.IView iView;
    private ShareArticleSelectContract.IModel iModel;

    public SharePresenter(ShareArticleSelectContract.IView iView){
        this.iView = iView;
        iModel = new ShareModel();
    }

    @Override
    public void getShareArticle(int page) {
        iModel.getShareArticleList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse<ShareData>>() {
                    @Override
                    public void accept(BaseResponse<ShareData> shareDataBaseResponse) throws Throwable {
                        Log.e("SHARE", "获取分享文章列表: "+shareDataBaseResponse.getData() );
                        iView.getShareArticleSuccess(shareDataBaseResponse.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.e("SHARE", "获取分享文章列表失败: "+throwable.getLocalizedMessage());
                        iView.getShareArticleFailure(throwable);
                    }
                });
    }
}
