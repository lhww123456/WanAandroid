package com.lhw.wanaandroid.ui.home;


import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BannerData;
import com.lhw.wanaandroid.bean.BaseBean;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 * Created by lhw
 * 功能描述：
 */
public interface HomeContract {

    interface IHomePresenter {

        void getBannerData();
        void getAarticleData(int page);
    }

    interface IHomeModel {
        /**
         * @return banner数据, article数据
         */
        Observable<BaseBean<List<BannerData>>> getBannerData();
        Observable<BaseBean<Articles>> getArticleData(int page);
    }

    interface IHomeView {
        /**
         * 成功拿到数据，失败抛出异常信息
         */
        void getBannerSuccess(List<BannerData> banners);

        void getArticleSuccess(List<ArticleDetail> datas);

        void getFailure(Throwable throwable);

    }
}