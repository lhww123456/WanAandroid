package com.lhw.wanaandroid.ui.system;

import com.lhw.wanaandroid.bean.BaseBean;
import com.lhw.wanaandroid.bean.TreeData;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface SystemContract {

    interface ISystemPresenter {

        void getSystemData();
    }

    interface ISystemModel {

        Observable<BaseBean<List<TreeData>>> getSystemData();

    }

    interface ISystemView {

        /**
         * 成功拿到数据，失败抛出异常信息
         */
        void getSystemSuccess(List<TreeData> data);

        void getFailure(Throwable throwable);

    }
}