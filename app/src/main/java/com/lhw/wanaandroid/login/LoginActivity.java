package com.lhw.wanaandroid.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.login.login.LoginFragment;
import com.lhw.wanaandroid.login.register.RegisterFragment;
import com.lhw.wanaandroid.util.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

/**使用以下代码网站完成切换
 * https://blog.csdn.net/jingzz1/article/details/102163021?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1-102163021-blog-121954315.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1-102163021-blog-121954315.pc_relevant_default&utm_relevant_index=1
 */
public class LoginActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ImageButton imageButton;
ImageView imageView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewPager2 = findViewById(R.id.viewpager2);
        tabLayout = findViewById(R.id.tablayout);
        imageView = findViewById(R.id.back_to_mine);

        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        List<Fragment> fragments = new ArrayList<>();
        LoginFragment fragment1 = new LoginFragment();
        RegisterFragment fragment2 = new RegisterFragment();
        fragments.add(fragment1);
        fragments.add(fragment2);

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

        viewPager2.setOffscreenPageLimit(2);
        new TabLayoutMediator(tabLayout, viewPager2, true,new TabLayoutMediator.OnConfigureTabCallback() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                //这里需要根据position修改tab的样式和文字等
                if (position == 1){
                    tab.setText("去注册");
                }
                else {
                    tab.setText("去登陆");
                }
            }
        }).attach();
        //最后一定要attach()
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}