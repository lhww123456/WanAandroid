package com.lhw.wanaandroid.login.login;

import com.lhw.wanaandroid.login.bean.BaseResponse;
import com.lhw.wanaandroid.login.bean.UserData;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginPresenter implements LoginContract.LoginPresenter {

    private  LoginContract.LoginModel loginModel;
    private  LoginContract.LoginView loginView;

    public LoginPresenter(LoginContract.LoginView loginView) {
           this.loginView = loginView;
           loginModel = new LoginModel();

    }

    @Override
    public void login(String username, String password) {

        loginModel.login(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse<UserData>>() {
                    @Override
                    public void accept(BaseResponse<UserData> baseResponse) throws Throwable {
                            loginView.OnLoginSuccess(baseResponse.getData());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        loginView.Failure(throwable);
                    }
                });
    }
}
