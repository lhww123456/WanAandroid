package com.lhw.wanaandroid.ui.nav.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.WebViewActivity;
import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.bean.NavCategoryBean;

import java.util.List;

public class NavRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements  NavItemAdapter.OnItemClickListener{

    private Context mContext;
    private List<NavCategoryBean>  list_data;
    private LayoutInflater inflater;

    public NavRecycleViewAdapter(Context mContext, List<NavCategoryBean> list_data) {
        this.mContext = mContext;
        this.list_data = list_data;
        this.inflater = LayoutInflater.from(mContext);
    }
    public void setNavData(List<NavCategoryBean> data) {
        this.list_data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_nav_item, parent, false);
        return new MyHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        myHolder.tv_title.setText(list_data.get(position).getName());

        List<ArticleDetail> navDetails = list_data.get(position).getArticles();
        NavItemAdapter navItemAdapter = new NavItemAdapter(myHolder.recyclerView, navDetails);
        navItemAdapter.setOnItemClickListener(this);
        myHolder.recyclerView.setAdapter(navItemAdapter);
        Log.d("TAG", "onBindViewHolder: "+ navDetails);
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        private TextView tv_title;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.item_text_recyclerview);
            tv_title = itemView.findViewById(R.id.title_nav_item);

            FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(mContext);
            //flexDirection 属性决定主轴的方向（即项目的排列方向）。类似 LinearLayout 的 vertical 和 horizontal。
            flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);//主轴为水平方向，起点在左端。
            recyclerView.setLayoutManager(flexboxLayoutManager);
        }
    }
    //每一项点击
    @Override
    public void onItemClick(ArticleDetail data) {
        Intent intent = new Intent(mContext, WebViewActivity.class);
        intent.putExtra(WebViewActivity.WEB_URL,data.getLink());
        mContext.startActivity(intent);
    }
}


