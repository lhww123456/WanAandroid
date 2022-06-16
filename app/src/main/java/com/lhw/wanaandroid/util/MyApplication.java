package com.lhw.wanaandroid.util;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    //定义全局的上下文对象
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

    }
    public static Context getGloableContext(){
        return mContext;
    }
}
