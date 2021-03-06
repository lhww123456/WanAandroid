package com.lhw.wanaandroid.ui.mine.coin;

import android.util.Log;

import com.lhw.wanaandroid.bean.BaseResponse;
import com.lhw.wanaandroid.bean.Coin;
import com.lhw.wanaandroid.bean.CoinBean;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CoinPresenter implements CoinContract.ICoinPresenter {
    private final CoinContract.ICoinView coinView;
    private final CoinModel coinModel;

    public CoinPresenter(CoinContract.ICoinView coinView) {
        this.coinView = coinView;
        coinModel = new CoinModel();
    }

    @Override
    public void getMyCoinList(int page) {
        coinModel.getMyCoinList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse<CoinBean>>() {
                    @Override
                    public void accept(BaseResponse<CoinBean> coinDataBeans) throws Throwable {
                        Log.d("TAG", "获取积分成功: "+coinDataBeans.toString());
                        coinView.getMyCoinListSuccess(coinDataBeans.getData().getDatas());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("TAG", "获取积分失败: "+throwable.getLocalizedMessage());

                        coinView.getFailure(throwable);
                    }
                });
    }

    @Override
    public void getMyCoinCoutn() {
        coinModel.getMyCoinCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse<Coin>>() {
                    @Override
                    public void accept(BaseResponse<Coin> coinBaseBean) throws Throwable {
                        coinView.getMyCoinCountSuccess(coinBaseBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        coinView.getFailure(throwable);
                    }
                });
    }
}

