package com.lhw.wanaandroid.login.login;

import com.lhw.wanaandroid.bean.BaseResponse;
import com.lhw.wanaandroid.bean.UserData;
import io.reactivex.rxjava3.core.Observable;

public interface LoginContract {

    interface ILoginPresenter{
        void login(String username,String password);
    }
    interface ILoginModel{
        Observable<BaseResponse<UserData>> login(String username, String password);
    }
    interface ILoginView{
        void OnLoginSuccess(UserData data);

       void Failure(Throwable throwable);
    }
}
