package com.lhw.wanaandroid.ui.system.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.bean.Children;
import com.lhw.wanaandroid.bean.TreeData;

import java.util.List;

public class SystemRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener,SystemItemAdapter.OnItemClickListener {
    private Context context;
    private List<TreeData> data;
    private RecyclerView recyclerView;
    private OnItemClickListener mListener;

    public SystemRecycleViewAdapter(RecyclerView recyclerView, Context context, List<TreeData> data) {
        this.context = context;
        this.data = data;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tree_first, parent, false);
        view2.setOnClickListener(this);
        return new TabHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        List<Children> childrenBean = data.get(position).getChildren();
        TabHolder tabHolder = (TabHolder) holder;
        tabHolder.tv_system_title.setText(data.get(position).getName());


        SystemItemAdapter systemItemAdapter
                = new SystemItemAdapter(tabHolder.recyclerView2, childrenBean,position);
        tabHolder.recyclerView2.setAdapter(systemItemAdapter);
        systemItemAdapter.setOnItemClickListener(this);
//
//        tabHolder.tv_system_content.setText("");
//        for (Children child : data.get(position).getChildren()) {
//            tabHolder.tv_system_content.append(child.getName() + "     ");
//        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setSystemData(List<TreeData> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    class TabHolder extends RecyclerView.ViewHolder {
        private TextView tv_system_title, tv_system_content;
        private RecyclerView recyclerView2;

        public TabHolder(@NonNull View itemView) {
            super(itemView);
            tv_system_title = itemView.findViewById(R.id.system_tv_title);
            recyclerView2 = itemView.findViewById(R.id.item_text_recyclerview);

            FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);
            //???????????????????????????
            flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
//            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
//            gridLayoutManager.setAutoMeasureEnabled(true);
            recyclerView2.setLayoutManager(flexboxLayoutManager);

        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            int id = recyclerView.getChildAdapterPosition(v);
            mListener.onItemClick(data.get(id), id);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(TreeData bean, int position);

        void onItemClick(Children data, TreeData treeData);

    }
    @Override
    public void onItemClick(Children data, int id) {
        mListener.onItemClick(data,this.data.get(id));
    }
}