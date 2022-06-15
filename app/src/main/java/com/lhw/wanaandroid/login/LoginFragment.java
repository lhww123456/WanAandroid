package com.lhw.wanaandroid.login;

import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.bean.BaseBean;
import com.lhw.wanaandroid.bean.UserData;
import com.lhw.wanaandroid.login.bean.BaseResponse;
import com.lhw.wanaandroid.login.bean.Data;
import com.lhw.wanaandroid.network.WanAndroidService;
import com.lhw.wanaandroid.observer.MyObserver;
import com.lhw.wanaandroid.ui.base.BaseFragment;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends BaseFragment {
    View view;
    private EditText et_username,et_password; //获取用户名密码输入框
    private String username, password;//获取用户名,密码
    private Button btn_login;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initSubViews() {
        et_username = (EditText) find(R.id.login_input_username);
        et_password = (EditText) find(R.id.login_input_pwd);
        btn_login = (Button) find(R.id.btn_login);

        MyReceiver myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(".MY_BROADCAST");
        getActivity().registerReceiver(myReceiver, intentFilter);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取参数
                username = et_username.getText().toString().trim();//获取输入的用户名
                if (!TextUtils.isEmpty(username)) {//获取密码，用户名后四位
                    password = username.substring(username.length() - 6);
                }
                //判断
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(getContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://www.wanandroid.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                            .build();
                    WanAndroidService wanAndroidService = retrofit.create(WanAndroidService.class);
                    Observable<BaseResponse> register = wanAndroidService.login(username, password);
                    register.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new MyObserver() {
                                @Override
                                public void success(Data successBean) {
                                    Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent();
                                    intent.putExtra("username", username);
                                    intent.setAction(".MY_BROADCAST");
                                    getActivity().sendBroadcast(intent);
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