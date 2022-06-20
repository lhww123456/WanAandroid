package com.lhw.wanaandroid.login.register;

import com.lhw.wanaandroid.bean.BaseResponse;
import com.lhw.wanaandroid.bean.UserData;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RegisterPresenter implements RegisterContract.IRegisterPresenter {

    private final RegisterModel registerModel;
    private RegisterContract.IRegisterView registerView;

    public RegisterPresenter(RegisterContract.IRegisterView registerView) {
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
                    public void accept(BaseResponse baseResponse) throws Throwable {
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
