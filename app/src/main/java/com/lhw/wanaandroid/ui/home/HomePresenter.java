package com.lhw.wanaandroid.ui.home;

import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BannerData;
import com.lhw.wanaandroid.bean.BaseResponse;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public  class HomePresenter implements HomeContract.IHomePresenter {
    private HomeContract.IHomeView homeView;
    private HomeContract.IHomeModel homeModel;

    public HomePresenter(HomeContract.IHomeView homeView){
        this.homeView = homeView;
        homeModel = new HomeModel();
    }

    @Override
    public void getBannerData() {
        homeModel.getBannerData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse<List<BannerData>>>() {
                    @Override
                    public void accept(BaseResponse<List<BannerData>> listBannerResponse) throws Throwable {

                        homeView.getBannerSuccess(listBannerResponse.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        homeView.getFailure(throwable);
                    }
                });
    }

    @Override
    public void getTopData() {
//        homeModel.getHomeTopList() .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<BaseResponse<Articles>>() {
//                    @Override
//                    public void accept(BaseResponse<Articles> articlesBaseResponse) throws Throwable {
//                        Articles data = articlesBaseResponse.getData();
//                        List<ArticleDetail> list = data.getDatas();
//                        homeView.getTopArticleSuccess(list);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Throwable {
//                        homeView.getFailure(throwable);
//                    }
//                });
    }

    @Override
    public void getAarticleData(int page) {
        homeModel.getArticleData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse<Articles>>() {
                    @Override
                    public void accept(BaseResponse<Articles> articlesBaseResponse) throws Throwable {
                        Articles data = articlesBaseResponse.getData();
                        List<ArticleDetail> list = data.getDatas();
                        homeView.getArticleSuccess(list);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        homeView.getFailure(throwable);
                    }
                });
    }

    @Override
    public void collectArticle(int id) {
        homeModel.collect(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse baseBean) throws Throwable {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        homeView.getFailure(throwable);
                    }
                });
    }

    @Override
    public void unCollectArticle(int id) {
        homeModel.uncollect(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse baseBean) throws Throwable {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        homeView.getFailure(throwable);
                    }
                });
    }


}
