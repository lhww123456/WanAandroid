package com.lhw.wanaandroid.ui.mine.coin;


import com.lhw.wanaandroid.bean.BaseBean;
import com.lhw.wanaandroid.bean.Coin;
import com.lhw.wanaandroid.bean.CoinBean;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.network.WanAndroidService;

import io.reactivex.rxjava3.core.Observable;

public class CoinModel implements CoinContract.ICoinModel {

    @Override
    public Observable<BaseBean<Coin>> getMyCoinCount() {
        return RetrofitClient.getInstance()
                .getService(WanAndroidService.class).getMyCoinCount();
    }

    @Override
    public Observable<BaseBean<CoinBean>> getMyCoinList(int page) {
        return RetrofitClient.getInstance()
                .getService(WanAndroidService.class).getMyCoinList(page);
    }
}
