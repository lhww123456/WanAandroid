package com.lhw.wanaandroid.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    private Ilogin ilogin;

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
    public interface Ilogin {
        void success(String username,int coin);

        void faild();

    }

    public void setIlogin(Ilogin ilogin) {
        this.ilogin = ilogin;
    }


}