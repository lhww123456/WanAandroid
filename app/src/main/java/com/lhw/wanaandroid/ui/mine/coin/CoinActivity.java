package com.lhw.wanaandroid.ui.mine.coin;


import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lhw.wanaandroid.ui.mine.coin.adapter.*;
import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.bean.BaseBean;
import com.lhw.wanaandroid.bean.Coin;
import com.lhw.wanaandroid.bean.CoinBean;
import com.lhw.wanaandroid.ui.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;


public class CoinActivity extends BaseActivity implements CoinContract.ICoinView {
    private MyCoinListRecycleViewAdapter myCoinListRecycleViewAdapter;
    private int page = 1;//从第1也开始
    AppCompatTextView tv_coin;
    private CoinPresenter coinPresenter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void initSubViews() {
        int coin = getIntent().getIntExtra("coin", 520);
        ImageButton imageButton =find(R.id.coin_back_finish);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView = find(R.id.rv_coin_list);
        tv_coin = find(R.id.tv_coin);
        tv_coin.setText(coin + "");
        linearLayoutManager = new LinearLayoutManager(this);
        List<CoinBean.CoinDataBean> dataList = new ArrayList<>();
        recyclerView.setLayoutManager(linearLayoutManager);
        myCoinListRecycleViewAdapter = new MyCoinListRecycleViewAdapter(this,dataList);
        recyclerView.setAdapter(myCoinListRecycleViewAdapter);

        coinPresenter = new CoinPresenter(this);
        coinPresenter.getMyCoinList(page);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coin;
    }

    @Override
    public void getMyCoinListSuccess(List<CoinBean.CoinDataBean> data) {
        myCoinListRecycleViewAdapter.setCoinData(data);
    }

    @Override
    public void getMyCoinCountSuccess(BaseBean<Coin> data) {

    }

    @Override
    public void getFailure(Throwable throwable) {

    }
}