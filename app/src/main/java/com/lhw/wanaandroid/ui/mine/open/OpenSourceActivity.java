package com.lhw.wanaandroid.ui.mine.open;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.WebViewActivity;
import com.lhw.wanaandroid.bean.OpenSource;
import com.lhw.wanaandroid.ui.base.BaseActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class OpenSourceActivity extends BaseActivity {


    public static void start(Context context) {

        Intent intent = new Intent(context, OpenSourceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_open;
    }

    @Override
    protected void initSubViews() {

        ListView myListView = findViewById(R.id.list_view);
        List<OpenSource> openSourceList = getOpenSourceDatas();
        myListView.setAdapter(new OpenSourceAdapter(openSourceList));
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OpenSourceActivity.this,WebViewActivity.class);
                intent.putExtra(WebViewActivity.WEB_URL,openSourceList.get(position).getUrl());
                startActivity(intent);
            }
        });
        myListView.setNestedScrollingEnabled(false);
    }

    private List<OpenSource> getOpenSourceDatas() {

        List<OpenSource> openSourceList = new ArrayList<>();

        openSourceList.add(new OpenSource(getString(R.string.open_source_tencent_tbs_title), getString(R.string.open_source_tencent_tbs_desc), "https://x5.tencent.com/tbs/sdk.html"));
        openSourceList.add(new OpenSource(getString(R.string.open_source_glide_title), getString(R.string.open_source_glide_desc), "https://github.com/bumptech/glide"));
        openSourceList.add(new OpenSource(getString(R.string.open_source_retrofit_title), getString(R.string.open_source_retrofit_desc), "https://github.com/square/retrofit"));
        openSourceList.add(new OpenSource(getString(R.string.open_source_retrofit_adapter_title), getString(R.string.open_source_retrofit_adapter_desc), "https://github.com/square/retrofit/tree/master/retrofit-adapters"));
        openSourceList.add(new OpenSource(getString(R.string.open_source_retrofit_gson_title), getString(R.string.open_source_retrofit_gson_desc), "https://github.com/square/retrofit/tree/master/retrofit-converters"));
        openSourceList.add(new OpenSource(getString(R.string.open_source_banner_title), getString(R.string.open_source_banner_desc), "https://github.com/youth5201314/banner"));
        openSourceList.add(new OpenSource(getString(R.string.open_source_actionbar_ex_title), getString(R.string.open_source_actionbar_ex_desc), "https://github.com/goweii/ActionBarEx"));
        openSourceList.add(new OpenSource(getString(R.string.open_source_refresh_title), getString(R.string.open_source_refresh_desc), "https://gitee.com/scwang90/SmartRefreshLayout"));
        openSourceList.add(new OpenSource(getString(R.string.open_source_circle_image_title), getString(R.string.open_source_circle_image_desc), "https://github.com/hdodenhof/CircleImageView"));
        openSourceList.add(new OpenSource(getString(R.string.open_source_mmkv_title), getString(R.string.open_source_mmkv_desc), "https://github.com/Tencent/MMKV"));
        openSourceList.add(new OpenSource(getString(R.string.open_source_flexbox_title), getString(R.string.open_source_flexbox_desc), "https://github.com/google/flexbox-layout"));
        openSourceList.add(new OpenSource(getString(R.string.open_source_top_table_title), getString(R.string.open_source_top_table_desc), "https://github.com/hackware1993/MagicIndicator"));
        openSourceList.add(new OpenSource(getString(R.string.open_source_swipe_menu_title), getString(R.string.open_source_swipe_menu_desc), "https://github.com/mcxtzhang/SwipeDelMenuLayout"));
        openSourceList.add(new OpenSource(getString(R.string.open_source_sugar_title), getString(R.string.open_source_sugar_desc), "https://github.com/chennaione/sugar"));
        openSourceList.add(new OpenSource(getString(R.string.open_source_multi_state_title), getString(R.string.open_source_multi_state_desc), "https://github.com/Kennyc1012/MultiStateView"));
        openSourceList.add(new OpenSource(getString(R.string.open_source_disklrucache_title), getString(R.string.open_source_disklrucache_desc), "https://github.com/JakeWharton/DiskLruCache"));
        return openSourceList;
    }
}