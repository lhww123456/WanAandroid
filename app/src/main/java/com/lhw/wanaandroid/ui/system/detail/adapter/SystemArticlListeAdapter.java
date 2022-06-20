package com.lhw.wanaandroid.ui.system.detail.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BaseResponse;
import com.lhw.wanaandroid.login.LoginActivity;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.network.WanAndroidService;
import com.lhw.wanaandroid.util.ToastUtil;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class SystemArticlListeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private final Context context;
    private int id;
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
    public SystemArticlListeAdapter(RecyclerView recyclerView, Context context, List<ArticleDetail> treeBeans) {
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
        treeArticleHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!RetrofitClient.getSharedPrefsCookiePersistor().loadAll().isEmpty()) {
                    if (treeArticleHolder.imageView.isSelected()) {
                        RetrofitClient.getInstance().getService(WanAndroidService.class).unCollectArticle(childrenBean.getId())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<BaseResponse<Articles>>() {
                                    @Override
                                    public void accept(BaseResponse<Articles> articlesBaseResponse) throws Throwable {
                                        ToastUtil.showToast("已取消收藏");
                                        treeArticleHolder.imageView.setSelected(false);
                                    }
                                });
                    } else {
                        RetrofitClient.getInstance().getService(WanAndroidService.class).collectArticle(childrenBean.getId())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<BaseResponse<Articles>>() {
                                    @Override
                                    public void accept(BaseResponse<Articles> articlesBaseResponse) throws Throwable {
                                        ToastUtil.showToast("已收藏");
                                        treeArticleHolder.imageView.setSelected(true);
                                    }
                                });
                    }
                } else {
                    context.startActivity(new Intent(context, LoginActivity.class));
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return treeBeanList.size();
    }


    class TreeArticleHolder extends RecyclerView.ViewHolder {
        private TextView tv_system_author,tv_system_title,tv_system_time,tv_system_type;
        private ImageView imageView;
        public TreeArticleHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.tree_img_collect);
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
