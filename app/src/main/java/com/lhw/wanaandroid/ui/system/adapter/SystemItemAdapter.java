package com.lhw.wanaandroid.ui.system.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.bean.Children;

import java.util.List;

public class SystemItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private int id;
    private RecyclerView recyclerView;
    private List<Children> children;
    private OnItemClickListener listener;

    public SystemItemAdapter(RecyclerView recyclerView, List<Children> children, int id) {
        this.children = children;
        this.recyclerView = recyclerView;
        this.id= id;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nav_item_, parent, false);
        view.setOnClickListener(this);
        return new ItemFlexHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemFlexHolder itemFlexHolder =(ItemFlexHolder) holder;
        itemFlexHolder.textView.setText(children.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return children.size();
    }


    //监听 点击
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Children data,int id);
    }

    @Override
    public void onClick(View view) {

        if (listener != null) {
            int position
                    = recyclerView.getChildAdapterPosition(view);
            listener.onItemClick(children.get(position),id);
        }
    }

    class ItemFlexHolder extends RecyclerView.ViewHolder{

        private  TextView textView;

        public ItemFlexHolder(@NonNull View itemView) {
            super(itemView);
             textView = itemView.findViewById(R.id.item_recycler_text);
        }
    }
}
