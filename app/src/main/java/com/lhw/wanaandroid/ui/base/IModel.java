package com.lhw.wanaandroid.ui.base;
import com.lhw.wanaandroid.network.WanAndroidService;


public interface IModel {
    /**
     * 使用RxRetrofit请求数据
     *
     * @return
     */
    WanAndroidService doRxRequest();

}
