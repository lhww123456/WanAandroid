package com.lhw.wanaandroid.ui.mine;



import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.login.LoginActivity;
import com.lhw.wanaandroid.login.MyReceiver;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.network.WanAndroidService;
import com.lhw.wanaandroid.ui.base.BaseFragment;
import com.lhw.wanaandroid.ui.mine.coin.CoinActivity;
import com.lhw.wanaandroid.util.ToastUtil;

public class MineFragment extends  BaseFragment implements View.OnClickListener{
    private ImageView imageView;
    private TextView textView;
    private int coincount;

    @Override //去登陆页面
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_go:
                Intent intent = new Intent(this.getContext(), LoginActivity.class);

                startActivity(intent);
                break;
            case R.id.ll_coin:
                Intent intent1 = new Intent(this.getContext(), CoinActivity.class);

                intent1.putExtra("coin",coincount);
                startActivity(intent1);
                break;
            case R.id.ll_collect:
                Intent intent2 = new Intent(this.getContext(), CoinActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_read_later:
                Intent intent3 = new Intent(this.getContext(), CoinActivity.class);
                startActivity(intent3);
                break;
            case R.id.ll_share:
                Intent intent4 = new Intent(this.getContext(), CoinActivity.class);
                startActivity(intent4);
                break;
            case R.id.ll_about_me:
                Intent intent5 = new Intent(this.getContext(), CoinActivity.class);
                startActivity(intent5);
                break;
            case R.id.ll_read_record:
                Intent intent6 = new Intent(this.getContext(), CoinActivity.class);
                startActivity(intent6);
                break;
            case R.id.logout:
                RetrofitClient.getInstance().getService(WanAndroidService.class).logout();
//                ToastUtil.showToast("退出登录成功");
                textView.setText("去登录");
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
        LinearLayout coin = find(R.id.ll_coin);
        LinearLayout logout = find(R.id.logout);
        logout.setOnClickListener(this);
        coin.setOnClickListener(this);
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
            public void success(String username,int coinCount) {
                textView.setText(username);
                coincount = coinCount;
            }

            @Override
            public void faild() {

            }
        });
    }


}