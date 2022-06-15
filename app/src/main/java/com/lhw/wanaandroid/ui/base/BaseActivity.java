package com.lhw.wanaandroid.ui.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initSubViews();
    }

    protected abstract void initSubViews();

    protected  abstract int getLayoutId();

    protected <T extends View> T find(@IdRes int  id){
        return findViewById(id);
    }
}