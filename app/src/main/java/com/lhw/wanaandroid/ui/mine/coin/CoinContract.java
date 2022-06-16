package com.lhw.wanaandroid.ui.mine.coin;


import com.lhw.wanaandroid.bean.BaseBean;
import com.lhw.wanaandroid.bean.Coin;
import com.lhw.wanaandroid.bean.CoinBean;


import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface CoinContract {

    interface ICoinPresenter{
        void getMyCoinList(int page);
        void getMyCoinCoutn();
    }

    interface ICoinModel{
        Observable<BaseBean<Coin>> getMyCoinCount();
        Observable<BaseBean<CoinBean>> getMyCoinList(int page);
    }

    interface ICoinView{
        void getMyCoinListSuccess(List<CoinBean.CoinDataBean> data);
        void getMyCoinCountSuccess(BaseBean<Coin> data);
        void getFailure(Throwable throwable);
    }
}
