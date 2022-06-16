package com.lhw.wanaandroid.login.login;

import com.lhw.wanaandroid.login.bean.BaseResponse;
import com.lhw.wanaandroid.login.bean.UserData;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.network.WanAndroidService;

import io.reactivex.rxjava3.core.Observable;

public class LoginModel implements LoginContract.LoginModel {
    public LoginModel() {

    }
    @Override
    public Observable<BaseResponse<UserData>> login(String username, String password) {
        return RetrofitClient.getInstance().getService(WanAndroidService.class).login(username,password);
    }
}
