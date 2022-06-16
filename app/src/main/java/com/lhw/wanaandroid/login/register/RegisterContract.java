package com.lhw.wanaandroid.login.register;

import com.lhw.wanaandroid.login.bean.BaseResponse;
import com.lhw.wanaandroid.login.bean.UserData;

import io.reactivex.rxjava3.core.Observable;

public interface RegisterContract {

    interface RegisterPresenter{
        void register(String username,String password,String repassword);
    };
    interface RegisterModel{
        Observable<BaseResponse<UserData>> register(String username, String password, String repassword);
    };
    interface RegisterView{

        void OnRegisterSuccess(UserData data);

        void OnRegisterError(Throwable throwable);
    };
}
