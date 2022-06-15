package com.lhw.wanaandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.backup.BackupAgent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {
    WebView mWebContent;
    TextView mTitle;
    ImageButton imageButton;
    Toolbar mToolBar;
    public final static String WEB_URL = "web_url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_WanAandroid);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        //获取控件id
        mWebContent =  findViewById(R.id.web_content);
        mTitle = findViewById(R.id.web_title);
        imageButton = findViewById(R.id.back_finish);
        imageButton.setOnClickListener(this);
        mToolBar = findViewById(R.id.toolBar);
        Intent intent = getIntent();

        String url = intent.getStringExtra(WEB_URL);
        mWebContent.loadUrl(url);

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}