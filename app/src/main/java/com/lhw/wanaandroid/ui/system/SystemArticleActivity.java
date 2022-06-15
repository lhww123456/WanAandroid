package com.lhw.wanaandroid.ui.system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

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
    private List<Children> mTreeDatas;
    private TreeData mTree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_WanAandroid);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_article);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            mTree = (TreeData) bundle.getSerializable("mtree");
        }
        if (mTree != null) {
            mTreeDatas = mTree.getChildren();
        }
        viewPager2 = findViewById(R.id.viewpager2);
        tabLayout = findViewById(R.id.tablayout);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < mTreeDatas.size(); i++) {
            fragments.add(TreeArticleFragment.newInstance(mTreeDatas.get(i).getId()));
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