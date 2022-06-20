package com.lhw.wanaandroid.login.login;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.bean.UserData;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.ui.base.BaseFragment;
import com.lhw.wanaandroid.util.MyApplication;
import com.lhw.wanaandroid.util.ToastUtil;

public class LoginFragment extends BaseFragment implements LoginContract.ILoginView, View.OnClickListener {

    private EditText et_username, et_password; //获取用户名密码输入框
    private String username, password;//获取用户名,密码
    private Button btn_login;
    private LoginPresenter loginPresenter;
    private static SharedPreferences mSharedPreferences;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initSubViews() {
        et_username = find(R.id.login_input_username);
        et_password = find(R.id.login_input_pwd);
        btn_login = find(R.id.btn_login);
        btn_login.setOnClickListener(this);
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public void OnLoginSuccess(UserData data) {
        SharedPreferences.Editor edit = LoginFragment.getSharedPreferences().edit();
        edit.putString("username", username)
                .putInt("coin", data.getCoinCount())
                .commit();
        Intent intent = new Intent();
        intent.putExtra("username", username);
        intent.putExtra("coin", data.getCoinCount());
        intent.setAction(".MY_BROADCAST");
        getActivity().sendBroadcast(intent);
        ToastUtil.showToast("登录成功咯~");
        getActivity().finish();
    }

    @Override
    public void Failure(Throwable throwable) {
        Log.e("TAG", "Failure: " + throwable.getMessage());
    }

    @Override
    public void onClick(View view) {
        //获取参数
        username = et_username.getText().toString().trim();//获取输入的用户名
        password = et_password.getText().toString().trim(); //获取密码
        if (username == null || password == null) {
            Toast.makeText(getContext(), "用户或密码不可为空", Toast.LENGTH_SHORT).show();
        } else {
            loginPresenter.login(username, password);
        }
    }

    public static SharedPreferences getSharedPreferences() {
        if (mSharedPreferences == null) {
            synchronized (RetrofitClient.class) {
                if (mSharedPreferences == null) {
                    mSharedPreferences = MyApplication.getGloableContext()
                            .getSharedPreferences("loginUser", Context.MODE_PRIVATE);
                }
            }
        }
        return mSharedPreferences;
    }

}
