package com.lhw.wanaandroid.ui.mine.coin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.bean.CoinBean;

import java.util.List;


public class MyCoinListRecycleViewAdapter extends RecyclerView.Adapter {

    List<CoinBean.CoinDataBean> beanList;
    private Context context;

    public MyCoinListRecycleViewAdapter(Context context,List<CoinBean.CoinDataBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }
    public void setCoinData(List<CoinBean.CoinDataBean> data) {
//        this.beanList.clear();
        this.beanList.addAll(data);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coin, parent, false);
        return new CoinList(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CoinBean.CoinDataBean coinDataBean = beanList.get(position);
        CoinList coinListholder = (CoinList)holder;
        String str_time = coinDataBean.getDesc();
        String substringtime = str_time.substring(0,19);
        String substringdesc = str_time.substring(19);

        coinListholder.tv_desc.setText(substringdesc);
        coinListholder.tv_time.setText(substringtime);
        coinListholder.tv_coin_int.setText("+  " + String.valueOf(coinDataBean.getCoinCount()));
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    class CoinList extends RecyclerView.ViewHolder{
        TextView tv_desc, tv_coin_int,tv_time;
        public CoinList(@NonNull View itemView) {
            super(itemView);
            tv_desc = itemView.findViewById(R.id.coin_desc);
            tv_coin_int = itemView.findViewById(R.id.icon_int);
            tv_time = itemView.findViewById(R.id.coin__time);

        }
    }
}
