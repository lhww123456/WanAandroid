package com.lhw.wanaandroid.login;

import android.content.Intent;

import android.text.TextUtils;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.login.bean.BaseResponse;
import com.lhw.wanaandroid.login.bean.Data;
import com.lhw.wanaandroid.network.WanAndroidService;
import com.lhw.wanaandroid.observer.MyObserver;
import com.lhw.wanaandroid.ui.base.BaseFragment;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterFragment extends BaseFragment  {
    View view;
    private EditText et_uname,et_pwd,et_repwd; //获取用户名密码输入框
    private String uname, pwd,repwd;//获取用户名,密码
    private Button btn_register;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initSubViews() {
        et_uname = (EditText) find(R.id.register_input_username);
        et_pwd = (EditText) find(R.id.register_input_pwd);
        et_repwd = (EditText) find(R.id.register_input_repwd);
        btn_register = (Button) find(R.id.btn_register);

    }



    @Override
    public void onStart() {
        super.onStart();
        btn_register = (Button) find(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取参数
                uname = et_uname.getText().toString().trim();//获取输入的用户名
                if (!TextUtils.isEmpty(uname)) {//获取密码，用户名后四位
                    pwd = uname.substring(uname.length() - 6);
                }
                //判断
                if (TextUtils.isEmpty(uname)) {
                    Toast.makeText(getContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://www.wanandroid.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                            .build();
                    WanAndroidService wanAndroidService = retrofit.create(WanAndroidService.class);
                    Observable<BaseResponse> register = wanAndroidService.register(uname, pwd, pwd);
                    register.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new MyObserver() {
                                @Override
                                public void success(Data successBean) {
                                    Toast.makeText(getContext(), "注册成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getContext(),LoginActivity.class);
                                    intent.putExtra("username",uname);
                                    startActivity(intent);
                                    //如果注册成功，那么就跳转回到登录界面
                                    getActivity().finish();
                                }

                                @Override
                                public void error(String message) {
                                    Toast.makeText(getContext(),message, Toast.LENGTH_SHORT).show();
                                }
                            });

                }

            }
        });
    }
}