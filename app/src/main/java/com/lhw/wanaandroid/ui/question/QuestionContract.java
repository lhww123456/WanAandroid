package com.lhw.wanaandroid.ui.question;


import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BaseBean;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 * Created by lhw
 * 功能描述：
 */
public interface QuestionContract {

    interface IQuestionPresenter{

        /**
         * <p>P层接口</p>
         * 获取数据
         */
        void getQuestionData(int page);
    }

    interface IQuestionModel {
        /**
         * <p>获取数据</p>
         * @return banner数据,article数据
         */
        Observable<BaseBean<Articles>> getQuestionData(int page);
    }

    interface IQuestionView  {

        /**
         * 成功拿到数据，失败抛出异常信息
         */
        void getQuestionSuccess(List<ArticleDetail> datas);
        void getFailure(Throwable throwable);


    }




}