package com.lhw.wanaandroid.login.register;

import com.lhw.wanaandroid.login.bean.BaseResponse;
import com.lhw.wanaandroid.login.bean.UserData;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RegisterPresenter implements RegisterContract.RegisterPresenter {

    private final RegisterModel registerModel;
    private RegisterContract.RegisterView registerView;

    public RegisterPresenter(RegisterContract.RegisterView registerView) {
        this.registerView =registerView;
        registerModel = new RegisterModel();
    }

    @Override
    public void register(String username, String password, String repassword) {
        registerModel.register(username,password,repassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse<UserData>>() {
                    @Override
                    public void accept(BaseResponse<UserData> baseResponse) throws Throwable {
                            registerView.OnRegisterSuccess((UserData) baseResponse.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                            registerView.OnRegisterError(throwable);
                    }
                });
    }
}
