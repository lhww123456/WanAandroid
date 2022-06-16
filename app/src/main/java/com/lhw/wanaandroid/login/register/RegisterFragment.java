package com.lhw.wanaandroid.login.register;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.login.LoginActivity;
import com.lhw.wanaandroid.login.bean.UserData;
import com.lhw.wanaandroid.ui.base.BaseFragment;


public class RegisterFragment extends BaseFragment implements RegisterContract.RegisterView, View.OnClickListener {

    private Button button;
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

        String username = et_uname.getText().toString().trim();
        String password = et_pwd.getText().toString().trim();
        String repassword = et_repwd.getText().toString().trim();
        if (username == null || password == null || repassword == null) {
            Toast.makeText(getContext(), "用户名或密码不可为空", Toast.LENGTH_SHORT).show();
        } else if (password == repassword) {
            Toast.makeText(getContext(), "两个密码不一致", Toast.LENGTH_SHORT).show();
        } else {
            registerPresenter.register(username, password, repassword);

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

    }
}
