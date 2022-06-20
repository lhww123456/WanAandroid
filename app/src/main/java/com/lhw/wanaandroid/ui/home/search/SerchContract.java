package com.lhw.wanaandroid.ui.home.search;

import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BannerData;
import com.lhw.wanaandroid.bean.BaseResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 * Created by lhw
 * 功能描述：
 */
public interface SerchContract {

    interface IHomePresenter {

        void getBannerData();

        void getTopData();

        void getAarticleData(int page);

        void collectArticle(int id);

        void unCollectArticle(int id);
    }

    interface IHomeModel {
        /**
         * @return banner数据, article数据
         */
        Observable<BaseResponse<List<BannerData>>> getBannerData();

        Observable<BaseResponse<Articles>> getArticleData(int page);

        Observable<BaseResponse<Articles>> getHomeTopList();

        Observable<BaseResponse<Articles>> collect(int id);

        Observable<BaseResponse<Articles>> uncollect(int id);

    }

    interface IHomeView {
        /**
         * 成功拿到数据，失败抛出异常信息
         */
        void getBannerSuccess(List<BannerData> banners);

        void getArticleSuccess(List<ArticleDetail> datas);

        void getTopArticleSuccess(List<ArticleDetail> datas);


        void getFailure(Throwable throwable);


    }

}
