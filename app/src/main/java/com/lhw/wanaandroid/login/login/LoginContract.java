package com.lhw.wanaandroid.login.login;

import com.lhw.wanaandroid.login.bean.BaseResponse;
import com.lhw.wanaandroid.login.bean.UserData;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface LoginContract {

    interface LoginPresenter{
        void login(String username,String password);
    }
    interface LoginModel{
        Observable<BaseResponse<UserData>> login(String username, String password);
    }
    interface LoginView{
        void OnLoginSuccess(UserData data);

       void Failure(Throwable throwable);
    }
}
