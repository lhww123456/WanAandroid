package com.lhw.wanaandroid.login.register;

import com.lhw.wanaandroid.bean.BaseResponse;
import com.lhw.wanaandroid.bean.UserData;
import io.reactivex.rxjava3.core.Observable;

public interface RegisterContract {

    interface IRegisterPresenter{
        void register(String username,String password,String repassword);
    }
    interface IRegisterModel{
        Observable<BaseResponse<UserData>> register(String username, String password, String repassword);
    }
    interface IRegisterView{

        void OnRegisterSuccess(UserData data);

        void OnRegisterError(Throwable throwable);
    }
}
