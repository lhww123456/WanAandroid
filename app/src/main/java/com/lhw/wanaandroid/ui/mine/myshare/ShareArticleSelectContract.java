package com.lhw.wanaandroid.ui.mine.myshare;

import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BaseResponse;
import com.lhw.wanaandroid.bean.ShareData;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;


public interface ShareArticleSelectContract {
    interface IView {
        void getShareArticleSuccess(ShareData articleDetail);
        void getShareArticleFailure(Throwable throwable);
    }

    interface IPresenter {
        void getShareArticle(int page);
    }

    interface IModel {
        Observable<BaseResponse<ShareData>> getShareArticleList(int page);
    }
}
