package com.lhw.wanaandroid.ui.system.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.bean.Children;
import com.lhw.wanaandroid.bean.TreeData;
import com.lhw.wanaandroid.util.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class SystemArticleActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private String mTitle;
    private int id;
    private int framid = 0;
    ImageView imageView;
    TextView titleview;
    private List<Children> mTreeDatas;
    private TreeData mTree;
    private Children childrendata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_WanAandroid);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_article);
        titleview = findViewById(R.id.tile_system);
        imageView = findViewById(R.id.back_to_system);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            mTree = (TreeData) bundle.getSerializable("mtree");
            childrendata = (Children) bundle.getSerializable("childdata");
        }
        if (mTree != null) {
            mTreeDatas = mTree.getChildren();
            titleview.setText( mTree.getName());
        }
        viewPager2 = findViewById(R.id.viewpager2);
        tabLayout = findViewById(R.id.tablayout);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);


        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < mTreeDatas.size(); i++) {
            if (childrendata != null) {
                if (mTreeDatas.get(i).getId() == childrendata.getId()) {
                    framid = i;
                }
            }
            fragments.add(SystemArticleFragment.newInstance(mTreeDatas.get(i).getId()));
        }

        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                Fragment fragment = fragments.get(position);
                return fragment;
            }
            @Override
            public int getItemCount() {
                return fragments.size();
            }
        });
        //设置当前选择的页面。
        // 如果 smoothScroll = true，将执行从当前项目到新项目的平滑动画。
        // 如果适配器未设置或为空，则静默忽略。将项目夹在适配器的边界上。
        // 参数： item - 选择的项目索引 smoothScroll - true 平滑滚动到新项目，false 立即过渡
        viewPager2.setCurrentItem(framid,true);


        viewPager2.setOffscreenPageLimit(mTreeDatas.size());
        new TabLayoutMediator(tabLayout, viewPager2, true,new TabLayoutMediator.OnConfigureTabCallback() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                //这里需要根据position修改tab的样式和文字等
                for (int i = 0; i <mTreeDatas.size() ; i++) {
                    tab.setText(mTreeDatas.get(position).getName());
                }
            }
        }).attach(); //最后一定要attach()
    }
}