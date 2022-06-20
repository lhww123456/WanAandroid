package com.lhw.wanaandroid.login.register;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.bean.UserData;
import com.lhw.wanaandroid.login.LoginActivity;
import com.lhw.wanaandroid.ui.base.BaseFragment;


public class RegisterFragment extends BaseFragment implements RegisterContract.IRegisterView, View.OnClickListener {

    private EditText et_uname,et_pwd,et_repwd; //获取用户名密码输入框
    private String uname, pwd,repwd;//获取用户名,密码
    private Button btn_register;
    private RegisterPresenter registerPresenter;

    @Override
    public void OnRegisterSuccess(UserData data) {

        Intent intent = new Intent(getContext(), LoginActivity.class);
        getActivity().sendBroadcast(intent);
        startActivity(intent);
        Toast.makeText(getContext(), "注册成功", Toast.LENGTH_SHORT).show();
        getActivity().finish();

    }
    @Override
    public void OnRegisterError(Throwable throwable) {
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

        uname = et_uname.getText().toString().trim();
        pwd = et_pwd.getText().toString().trim();
        repwd = et_repwd.getText().toString().trim();
        if (uname == null || pwd == null || repwd == null) {
            Toast.makeText(getContext(), "用户名或密码不可为空", Toast.LENGTH_SHORT).show();
        } else if (pwd == repwd) {
            Toast.makeText(getContext(), "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
        } else {
            registerPresenter.register(uname, pwd, repwd);
        }

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initSubViews() {
        et_uname =  find(R.id.register_input_username);
        et_pwd =  find(R.id.register_input_pwd);
        et_repwd =  find(R.id.register_input_repwd);
        btn_register =  find(R.id.btn_register);
        btn_register.setOnClickListener(this);
        registerPresenter = new RegisterPresenter(this);

    }
}
