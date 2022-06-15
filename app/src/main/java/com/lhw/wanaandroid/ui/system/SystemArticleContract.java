package com.lhw.wanaandroid.ui.system;

import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BaseBean;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface SystemArticleContract {

    interface ISystemArticlePresenter {

        /**
         * <p>P层接口</p>
         * 获取数据
         */
        void getSystemArticleData(int page,int cid);
    }

    interface ISystemArticleModel {
        Observable<BaseBean<Articles>>  getArticleData(int page, int cid);
    }

    interface ISystemArticleView {

        /**
         * 成功拿到数据，失败抛出异常信息
         */
        void getSystemArticleSuccess(List<ArticleDetail> datas);
        void getFailure(Throwable throwable);

    }
}