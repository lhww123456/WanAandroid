package com.lhw.wanaandroid.ui.system;

import android.util.Log;

import com.lhw.wanaandroid.bean.BaseBean;
import com.lhw.wanaandroid.bean.TreeData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SystemPresenter implements SystemContract.ISystemPresenter {
    private SystemContract.ISystemView systemView;
    private SystemContract.ISystemModel systemModel;

    public SystemPresenter(SystemContract.ISystemView systemView) {
        this.systemView = systemView;
        systemModel = new SystemModel();
    }

    public void getSystemData() {
        systemModel.getSystemData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<List<TreeData>>>() {
                    @Override
                    public void accept(BaseBean<List<TreeData>> listBaseBean) throws Throwable {
                        Log.e("lhww+system体系", "accept: " + listBaseBean.getData().toString());
                        systemView.getSystemSuccess(listBaseBean.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.e("TAG", "accept: " + throwable.getLocalizedMessage());
                        systemView.getFailure(throwable);
                    }
                })
        ;
    }
}
