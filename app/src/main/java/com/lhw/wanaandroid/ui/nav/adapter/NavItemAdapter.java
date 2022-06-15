package com.lhw.wanaandroid.ui.nav.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.bean.ArticleDetail;

import java.util.ArrayList;
import java.util.List;

public class NavItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private  RecyclerView recyclerView;
    private List<ArticleDetail> navDetails;
    private OnItemClickListener listener;

    public NavItemAdapter(RecyclerView recyclerView, List<ArticleDetail> navDetails) {
        this.navDetails = navDetails;
        this.recyclerView = recyclerView;
    }
    @Override
    public int getItemCount() {
        return navDetails.size();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flex_item_nav, parent, false);
        view.setOnClickListener(this);
        return new ItemButtonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemButtonHolder itemButtonHolder =(ItemButtonHolder) holder;
        itemButtonHolder.textView.setText(navDetails.get(position).getTitle());
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(ArticleDetail data);
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            int position = recyclerView.getChildAdapterPosition(view);
            listener.onItemClick(navDetails.get(position));
        }
    }

    class ItemButtonHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public ItemButtonHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_recycler_text);
        }
    }
}
