package com.lhw.wanaandroid.observer;



import com.lhw.wanaandroid.login.bean.BaseResponse;
import com.lhw.wanaandroid.login.bean.Data;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


public abstract class MyObserver implements Observer<BaseResponse> {

    public abstract void success(Data successBean);

    public abstract void error(String message);


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseResponse baseResponse) {
        if (baseResponse.getData() == null) {
            error(baseResponse.getErrorMsg());

        } else {
            success((baseResponse.getData()));
        }
    }

    @Override
    public void onError(Throwable e) {
        error("异常类型为：" + e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}


