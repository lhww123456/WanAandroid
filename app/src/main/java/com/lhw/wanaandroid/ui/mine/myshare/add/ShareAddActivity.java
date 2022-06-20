package com.lhw.wanaandroid.ui.mine.myshare.add;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.WebViewActivity;
import com.lhw.wanaandroid.bean.BaseResponse;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.network.WanAndroidService;
import com.lhw.wanaandroid.ui.base.BaseActivity;
import com.lhw.wanaandroid.util.ToastUtil;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import org.w3c.dom.Text;

import javax.security.auth.login.LoginException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ShareAddActivity extends BaseActivity implements View.OnClickListener {

    private TextView updata, openLink;
    private EditText link;
    private EditText title;
    private Button btn;
    private String strlink;
    private String strtitle;
    private Toolbar toolbar;

    @Override
    protected void initSubViews() {
        toolbar = find(R.id.add_share_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title = find(R.id.share_title);
        link = find(R.id.share_link);
        updata = find(R.id.updata_title);
        updata.setOnClickListener(this);
//        openLink = find(R.id.open_link);
        openLink.setOnClickListener(this);
        btn = find(R.id.share_btn);
        btn.setOnClickListener(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_share_add;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.updata_title:
                title.setText("");
                break;
//            case R.id.open_link:
//                strtitle = title.getText().toString();
//                if (strtitle.isEmpty()) {
//                    Toast.makeText(this, "打开的链接为空", Toast.LENGTH_SHORT).show();
//                } else {
//                    Intent intent = new Intent(this, WebViewActivity.class);
//                    intent.putExtra(WebViewActivity.WEB_URL, strlink);
//                    startActivity(intent);
//                }
//                break;
            case R.id.share_btn:
                strtitle = title.getText().toString();
                strlink = link.getText().toString();
                RetrofitClient.getInstance().getService(WanAndroidService.class)
                        .shareArticle(strtitle, strlink)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<BaseResponse>() {
                            @Override
                            public void accept(BaseResponse shareDataBaseResponse) throws Throwable {
                                finish();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                ToastUtil.showToast(throwable.getMessage());
                            }
                        });
                break;
        }
    }
}
