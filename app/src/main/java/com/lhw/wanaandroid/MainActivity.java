package com.lhw.wanaandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lhw.wanaandroid.ui.home.HomeFragment;
import com.lhw.wanaandroid.ui.mine.MineFragment;
import com.lhw.wanaandroid.ui.nav.NavFragment;
import com.lhw.wanaandroid.ui.question.QuestionFragment;
import com.lhw.wanaandroid.ui.system.SystemFragment;
import com.lhw.wanaandroid.util.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ViewPager2 viewPager2;//视图翻页工具，提供了多页面切换的效果。
    private LinearLayout llIndex,llCollection,llRankings,llNav,llMine;//底部框四个选择"按钮"（实际只是图片）所在的小布局，给这些布局加点击事件，实现点击效果
    private ImageView ivIndex,ivCollection,ivRaknings,iNva,ivMine,ivCurrent;//四个点击区的图片
    //全局的view最后在onCreateView()里要返回，但要在onCreateView()外面通过要view实现对控件的实例化，只能先拿出来，变成全局变量。
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_WanAandroid);
        setContentView(R.layout.activity_main);

        //初始化方法，ViewPager的初始化在其中完成
        initPager();
        initNavView();
    }

    private void initPager() {
        //定义一个ViewPager2
        viewPager2 = findViewById(R.id.viewPager);
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());

        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new HomeFragment());
        fragments.add(new QuestionFragment());
        fragments.add(new SystemFragment());
        fragments.add(new NavFragment());
        fragments.add(new MineFragment());

        //ViewPager2构建Adapter
        MyFragmentPagerAdapter myFragmentPagerAdapter =
                new MyFragmentPagerAdapter(getSupportFragmentManager(),getLifecycle(),fragments);
        viewPager2.setAdapter(myFragmentPagerAdapter);
        //ViewPager2提供的滑动监听
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeNav(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void changeNav(int position) {
        ivCurrent.setSelected(false);
        switch (position){
            case R.id.home:
                viewPager2.setCurrentItem(0);
            case 0:
                ivIndex.setSelected(true);
                ivCurrent=ivIndex;

                break;
            case R.id.interlocution:
                viewPager2.setCurrentItem(1);
            case 1:
                ivCollection.setSelected(true);
                ivCurrent=ivCollection;
                break;
            case R.id.system:
                viewPager2.setCurrentItem(2);
            case 2:
                ivRaknings.setSelected(true);
                ivCurrent=ivRaknings;
                break;
            case R.id.nav:
                viewPager2.setCurrentItem(3);
            case 3:
                iNva.setSelected(true);
                ivCurrent=iNva;
                break;
            case R.id.mine:
                viewPager2.setCurrentItem(4);
            case 4:
                ivMine.setSelected(true);
                ivCurrent=ivMine;
                break;
        }
    }

    private void initNavView() {
        llIndex = findViewById(R.id.home);
        llIndex.setOnClickListener(this);

        llCollection = findViewById(R.id.interlocution);
        llCollection.setOnClickListener(this);

        llRankings = findViewById(R.id.system);
        llRankings.setOnClickListener(this);

        llNav = findViewById(R.id.nav);
        llNav.setOnClickListener(this);

        llMine = findViewById(R.id.mine);
        llMine.setOnClickListener(this);

        ivIndex = findViewById(R.id.img_home);
        ivCollection = findViewById(R.id.img_interlocution);
        ivRaknings = findViewById(R.id.img_system);
        iNva = findViewById(R.id.img_nav);
        ivMine = findViewById(R.id.img_mine);

        ivIndex.setSelected(true);
        ivCurrent=ivIndex;
    }

    @Override
    public void onClick(View v) {
        changeNav(v.getId());
    }

}