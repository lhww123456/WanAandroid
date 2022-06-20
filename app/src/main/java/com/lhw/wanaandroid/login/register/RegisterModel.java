package com.lhw.wanaandroid.login.register;


import com.lhw.wanaandroid.bean.BaseResponse;
import com.lhw.wanaandroid.bean.UserData;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.network.WanAndroidService;

import io.reactivex.rxjava3.core.Observable;


public class RegisterModel implements RegisterContract.IRegisterModel {

    @Override
    public Observable<BaseResponse<UserData>> register(String username, String password, String repassword) {
        return RetrofitClient.getInstance()
                .getService(WanAndroidService.class)
                .register(username,password,repassword);
    }
}
