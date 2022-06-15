package com.lhw.wanaandroid.ui.system.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.bean.ArticleDetail;

import java.util.List;


public class TreeArticlListeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private final Context context;
    private final RecyclerView recyclerView;
    private List<ArticleDetail> treeBeanList;
    private OnItemClickListener listener;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tree_articlelist, parent, false);
        view.setOnClickListener(this);
        return new TreeArticleHolder(view);
    }
    public TreeArticlListeAdapter(RecyclerView recyclerView, Context context, List<ArticleDetail> treeBeans) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.treeBeanList = treeBeans;
    }
    public void setArticle(List<ArticleDetail> data) {
//        this.treeBeanList.clear();
        this.treeBeanList.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ArticleDetail childrenBean = treeBeanList.get(position);
        TreeArticleHolder treeArticleHolder = (TreeArticleHolder) holder;
        treeArticleHolder.tv_system_title.setText(childrenBean.getTitle());
        treeArticleHolder.tv_system_time.setText(childrenBean.getNiceDate());
        treeArticleHolder.tv_system_type.setText(childrenBean.getChapterName());
        treeArticleHolder.tv_system_author.setText(childrenBean.getAuthor());
    }
    @Override
    public int getItemCount() {
        return treeBeanList.size();
    }


    class TreeArticleHolder extends RecyclerView.ViewHolder {
        private TextView tv_system_author,tv_system_title,tv_system_time,tv_system_type;

        public TreeArticleHolder(@NonNull View itemView) {
            super(itemView);
            tv_system_author = itemView.findViewById(R.id.tree_tv_author);
            tv_system_title= itemView.findViewById(R.id.tree_tv_title);
            tv_system_time = itemView.findViewById(R.id.tree_tv_time);
            tv_system_type = itemView.findViewById(R.id.tree_tv_type);
        }
    }

    //监听 点击
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
            listener.onItemClick(treeBeanList.get(position));
        }
    }
}
