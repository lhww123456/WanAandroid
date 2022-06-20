package com.lhw.wanaandroid.ui.mine.about;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.WebViewActivity;
import com.lhw.wanaandroid.bean.OpenSource;
import com.lhw.wanaandroid.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class AboutMeActivity extends BaseActivity {


    @Override
    protected void initSubViews() {
        ListView myListView = findViewById(R.id.list_view_mine);
        List<OpenSource> openSourceList = getOpenSourceDatas();
        myListView.setAdapter(new AboutMeAdapter(openSourceList));
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AboutMeActivity.this, WebViewActivity.class);
                intent.putExtra(WebViewActivity.WEB_URL,openSourceList.get(position).getUrl());
                startActivity(intent);
            }
        });
        myListView.setNestedScrollingEnabled(false);
    }
    private List<OpenSource> getOpenSourceDatas() {

        List<OpenSource> openSourceList = new ArrayList<>();
        openSourceList.add(new OpenSource(getString(R.string.mine_host1), getString(R.string.mine_host_1), "https://www.wanandroid.com/blog/show/2"));
        openSourceList.add(new OpenSource(getString(R.string.mine_host2), getString(R.string.mine_host_2), "https://www.codercto.com/a/30257.html"));
        return openSourceList;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_me;
    }
}