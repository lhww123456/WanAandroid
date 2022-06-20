package com.lhw.wanaandroid.login.login;

import com.lhw.wanaandroid.bean.BaseResponse;
import com.lhw.wanaandroid.bean.UserData;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.network.WanAndroidService;

import io.reactivex.rxjava3.core.Observable;

public class LoginModel implements LoginContract.ILoginModel {
    public LoginModel() {

    }
    @Override
    public Observable<BaseResponse<UserData>> login(String username, String password) {
        return RetrofitClient.getInstance().getService(WanAndroidService.class).login(username,password);
    }
}
