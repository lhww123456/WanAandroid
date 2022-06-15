package com.lhw.wanaandroid.ui.nav;


import com.lhw.wanaandroid.bean.BaseBean;
import com.lhw.wanaandroid.bean.NavCategoryBean;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

/**
 * Created by lhw
 * 功能描述：
 */
public interface NavContract {

    interface INavPresenter{
        void getNavData();
    }

    interface INavModel {
        Observable<BaseBean<List<NavCategoryBean>>> getNavData();
    }

    interface INavView  {

        /**
         * 成功拿到数据，失败抛出异常信息
         */
        void getNavSuccess(List<NavCategoryBean> datas);
        void getFailure(Throwable throwable);
    }

}