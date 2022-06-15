package com.lhw.wanaandroid.ui.system;

import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BaseBean;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SystemArticlePresenter implements SystemArticleContract.ISystemArticlePresenter {
    private SystemArticleContract.ISystemArticleView systemView;
    private SystemArticleContract.ISystemArticleModel systemModel;

    public SystemArticlePresenter(SystemArticleContract.ISystemArticleView systemView) {
        this.systemView = systemView;
        systemModel = new SystemArticleModel();
    }

    @Override
    public void getSystemArticleData(int page ,int cid) {
        systemModel.getArticleData(page, cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<Articles>>() {
                    @Override
                    public void accept(BaseBean<Articles> articleBaseBean) throws Throwable {
                        Articles data = articleBaseBean.getData();
                        List<ArticleDetail> datas = data.getDatas();
                        systemView.getSystemArticleSuccess(datas);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        systemView.getFailure(throwable);
                    }
                });
    }
}
