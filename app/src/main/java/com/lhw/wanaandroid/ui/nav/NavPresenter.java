package com.lhw.wanaandroid.ui.nav;

import android.util.Log;

import com.lhw.wanaandroid.bean.BaseBean;
import com.lhw.wanaandroid.bean.NavCategoryBean;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public  class NavPresenter implements NavContract.INavPresenter {
    private NavContract.INavView navView;
    private NavContract.INavModel navModel;

    public NavPresenter(NavContract.INavView navView) {
        this.navView = navView;
        navModel = new NavModel();
    }

    @Override
    public void getNavData() {
        navModel.getNavData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<List<NavCategoryBean>>>() {
                               @Override
                               public void accept(BaseBean<List<NavCategoryBean>> navCategoryBeanNavBean) throws Throwable {
                                   navView.getNavSuccess(navCategoryBeanNavBean.getData());
                                   Log.e("lhhww", "成功: "+navCategoryBeanNavBean.getData() );
                               }
                           }
                        , new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                navView.getFailure(throwable);
                                Log.e("lhww", "错误: " + throwable.getLocalizedMessage());

                            }
                        });
    }
}
