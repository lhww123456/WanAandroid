package com.lhw.wanaandroid.ui.mine.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.login.login.LoginFragment;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.network.WanAndroidService;
import com.lhw.wanaandroid.ui.base.BaseActivity;
import com.lhw.wanaandroid.util.ToastUtil;

public class SettingActivity extends BaseActivity {
    LinearLayout logout;

    @Override
    protected void initSubViews() {
        ImageView back;
        back = find(R.id.back_finish_mine);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        logout = find(R.id.ll_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitClient.getInstance().getService(WanAndroidService.class).logout();
                RetrofitClient.getClearableCookieJar().clear();
                LoginFragment.getSharedPreferences().edit().clear().commit();
                ToastUtil.showToast("退出登录成功");
                finish();

               /* textView.setText("去登录");*/
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }
}