package com.lhw.wanaandroid.ui.mine.mycollection.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lhw.wanaandroid.login.MyReceiver;

public class RefreshReceiver  extends BroadcastReceiver {
    private MyReceiver.Ilogin ilogin;

    @Override
    public void onReceive(Context context, Intent intent) {
        String username = intent.getStringExtra("username");
        int coinCount = intent.getIntExtra("coin",0);
        if (username != null && ilogin != null) { //当用户名不为空，登陆成功
            ilogin.success(username,coinCount);
        } else if (ilogin != null) {
            ilogin.faild();
        }
    }

    //定义接口
    public interface IActivityUpData {
        void upDataUi();
    }

    public void IActivityUpData(MyReceiver.Ilogin ilogin) {
        this.ilogin = ilogin;
    }
}
