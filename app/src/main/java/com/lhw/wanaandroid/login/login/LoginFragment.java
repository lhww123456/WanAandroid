package com.lhw.wanaandroid.login.login;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.login.bean.UserData;
import com.lhw.wanaandroid.ui.base.BaseFragment;

public class LoginFragment extends BaseFragment implements LoginContract.LoginView, View.OnClickListener {

    View view;
    private EditText et_username,et_password; //获取用户名密码输入框
    private String username, password;//获取用户名,密码
    private Button btn_login;
    private LoginPresenter loginPresenter;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initSubViews() {
        et_username = find(R.id.login_input_username);
        et_password =  find(R.id.login_input_pwd);
        btn_login =  find(R.id.btn_login);
        btn_login.setOnClickListener(this);
        loginPresenter = new LoginPresenter(this);

    }

    @Override
    public void OnLoginSuccess(UserData data) {
        Intent intent = new Intent();
        intent.putExtra("username", username);
        intent.putExtra("coin",data.getCoinCount());
        intent.setAction(".MY_BROADCAST");
        getActivity().sendBroadcast(intent);
        Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void Failure(Throwable throwable) {
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
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

}
