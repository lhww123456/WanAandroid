package com.lhw.wanaandroid.ui.mine;



import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.login.LoginActivity;
import com.lhw.wanaandroid.login.MyReceiver;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.network.WanAndroidService;
import com.lhw.wanaandroid.ui.base.BaseFragment;

public class MineFragment extends  BaseFragment implements View.OnClickListener{
    private ImageView imageView;
    private TextView textView;


    @Override //去登陆页面
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_go:
                Intent intent = new Intent(this.getContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.logout:
                RetrofitClient.getInstance().getService(WanAndroidService.class).logout();
                textView.setText("去登录");
//                textView.
                break;
        }

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initSubViews() {
        imageView =  find(R.id.img_avatar);//去登陆上方的图片
        textView =  find(R.id.tv_name);//去登录
        LinearLayout linearLayout = find(R.id.login_go);
        LinearLayout logout = find(R.id.logout);
        logout.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        //获取要刷新页面的控件

        MyReceiver myReceiver = new MyReceiver();
        //在需要注册广播的时候调用
        //新建一个IntentFilter，意图过滤器
        IntentFilter filter = new IntentFilter();
        //增加亮屏的行为action
        filter.addAction(".MY_BROADCAST");
        //注册广播，广播接受者是myReceiver，意图过滤器是filter
        getContext().registerReceiver(myReceiver, filter);

        myReceiver.setIlogin(new MyReceiver.Ilogin() {
            @Override
            public void success(String username) {
                textView.setText(username);
            }

            @Override
            public void faild() {

            }
        });
    }


}