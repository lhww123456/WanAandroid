package com.lhw.wanaandroid.ui.mine;


import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.login.LoginActivity;
import com.lhw.wanaandroid.login.MyReceiver;
import com.lhw.wanaandroid.login.login.LoginFragment;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.ui.base.BaseFragment;
import com.lhw.wanaandroid.ui.mine.about.AboutActivity;
import com.lhw.wanaandroid.ui.mine.about.AboutMeActivity;
import com.lhw.wanaandroid.ui.mine.coin.CoinActivity;
import com.lhw.wanaandroid.ui.mine.mycollection.ArticleCollectionActivity;
import com.lhw.wanaandroid.ui.mine.myshare.ShareActivity;
import com.lhw.wanaandroid.ui.mine.open.OpenSourceActivity;
import com.lhw.wanaandroid.ui.mine.setting.SettingActivity;
import com.lhw.wanaandroid.util.ToastUtil;


public class MineFragment extends BaseFragment implements View.OnClickListener {
    private ImageView imageView;
    private TextView textView, coinview;
    private int coincount;
    LinearLayout login_go, ll_setting, ll_coin, ll_about_me, logout, ll_open, ll_collect, ll_share, ll_book, ll_histoty;

    @Override //去登陆页面
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_go_name:
                if (textView.getText().toString().equals("去登录")) {
                    Intent intent = new Intent(this.getContext(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_coin:
                if (!RetrofitClient.getSharedPrefsCookiePersistor().loadAll().isEmpty()) {
                    Intent intent1 = new Intent(getActivity(), CoinActivity.class);
                    intent1.putExtra("coin", coincount);
                    startActivity(intent1);
                } else {
                    ToastUtil.showToast("请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;

            case R.id.ll_collect:
                if (!RetrofitClient.getSharedPrefsCookiePersistor().loadAll().isEmpty()) {
                    Intent intent2 = new Intent(getActivity(), ArticleCollectionActivity.class);
                    startActivity(intent2);
                } else {
                    ToastUtil.showToast("请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;

            case R.id.ll_history:
                Intent intent3 = new Intent(this.getContext(), CoinActivity.class);
                startActivity(intent3);
                break;

            case R.id.ll_share:
                if (!RetrofitClient.getSharedPrefsCookiePersistor().loadAll().isEmpty()) {
                    Intent intent4 = new Intent(getActivity(), ShareActivity.class);
                    startActivity(intent4);
                } else {
                    ToastUtil.showToast("请先登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;

            case R.id.ll_about_me:
                Intent intent5 = new Intent(this.getContext(), AboutMeActivity.class);
                startActivity(intent5);
                break;

            case R.id.ll_book:
                Intent intent6 = new Intent(this.getContext(), CoinActivity.class);
                startActivity(intent6);
                break;
            case R.id.ll_open:
                Intent intent8 = new Intent(this.getContext(), OpenSourceActivity.class);
                startActivity(intent8);
                break;

            case R.id.ll_setting:
                Intent intent7 = new Intent(this.getContext(), SettingActivity.class);
                startActivity(intent7);

                break;
        }

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initSubViews() {
        imageView = find(R.id.img_avatar);//去登陆上方的图片
        coinview = find(R.id.tv_coin1);
        textView = find(R.id.login_go_name);//去登录
        textView.setOnClickListener(this);

        ll_collect = find(R.id.ll_collect);//收藏
        ll_collect.setOnClickListener(this);

        ll_coin = find(R.id.ll_coin);//积分
        ll_coin.setOnClickListener(this);

        ll_about_me = find(R.id.ll_about_me);//关于我
        ll_about_me.setOnClickListener(this);

        ll_setting = find(R.id.ll_setting);//系统设置，退出登录
        ll_setting.setOnClickListener(this);

        ll_histoty = find(R.id.ll_history);//历史
        ll_histoty.setOnClickListener(this);

        ll_open = find(R.id.ll_open);
        ll_open.setOnClickListener(this);

        ll_book = find(R.id.ll_book);//书签
        ll_book.setOnClickListener(this);

        ll_share = find(R.id.ll_share);//分享
        ll_share.setOnClickListener(this);
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
            public void success(String username, int coinCount) {
                textView.setText(username);
                coincount = coinCount;
                coinview.setText(coinCount + "");
            }

            @Override
            public void faild() {

            }
        });

        if (!RetrofitClient.getSharedPrefsCookiePersistor().loadAll().isEmpty()) {
            SharedPreferences sharedPreferences = LoginFragment.getSharedPreferences();
            textView.setText(sharedPreferences.getString("username", ""));
            coincount = sharedPreferences.getInt("coin", 520);
        }
    }

}