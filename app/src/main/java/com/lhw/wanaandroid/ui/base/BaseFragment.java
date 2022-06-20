package com.lhw.wanaandroid.ui.base;

import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public abstract class BaseFragment extends Fragment {

    View containerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        containerView = inflater.inflate(getContentViewId(), container, false);
        initSubViews();
        return containerView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 获取布局文件
     */
    protected abstract int getContentViewId();

    /**
     * 初始化控件
     */
    protected abstract void initSubViews();

    protected <T extends View> T find(@IdRes int id) {
        return containerView.findViewById(id);
    }

}
