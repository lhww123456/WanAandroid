package com.lhw.wanaandroid.ui.mine.mycollection;

import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BaseResponse;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CollectPresenter implements CollectContract.IPresenter{

    private CollectContract.IView iView;
    private CollectContract.IModel iModel;

    public CollectPresenter(CollectContract.IView iView){
        this.iView = iView;
        iModel = new CollectModel();
    }

    @Override
    public void getCollectArticlePage(int page) {
        iModel.getCollectArticleList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse<Articles>>() {
                    @Override
                    public void accept(BaseResponse<Articles> articlesBaseBean) throws Throwable {
                        iView.getCollectArticleSuccess(articlesBaseBean.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        iView.getCollectArticleFailure(throwable);
                    }
                });
    }
}
