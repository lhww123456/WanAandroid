package com.lhw.wanaandroid.ui.mine.mycollection;

import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BaseResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;


public interface CollectContract {
    interface IView {

        void getCollectArticleSuccess(Articles articles);
        void getCollectArticleFailure(Throwable throwable);
    }

    interface IPresenter {
        void getCollectArticlePage(int page);
    }

    interface IModel {
        Observable<BaseResponse<Articles>> getCollectArticleList(int page);
    }
}
