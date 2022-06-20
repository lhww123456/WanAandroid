package com.lhw.wanaandroid.ui.mine.mycollection.adapter;

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
import com.lhw.wanaandroid.ui.mine.myshare.adapter.MyShareListAdapter;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CollectRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context context;
    private RecyclerView recyclerView;
    private List<ArticleDetail> dataData;
    private OnItemClickListener listener;

    public CollectRecyclerViewAdapter(RecyclerView recyclerView, Context context, List<ArticleDetail> dataData) {
        this.context = context;
        this.dataData = dataData;
        this.recyclerView = recyclerView;

    }

    public void setArticleData(List<ArticleDetail> dataData) {
//        this.dataData.clear();
        this.dataData.addAll(dataData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.item_home_item, parent, false);
        view.setOnClickListener(this);
        return new CollectViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ArticleDetail datasBean = dataData.get(position);
        CollectViewHolder viewHolder2 = (CollectViewHolder) holder;

        viewHolder2.tv_title.setText(datasBean.getTitle());
        viewHolder2.tv_shareUser.setText(datasBean.getShareUser());
        viewHolder2.tv_author.setText(datasBean.getAuthor());
        viewHolder2.tv_time.setText(datasBean.getNiceDate());
        viewHolder2.tv_type.setText(datasBean.getSuperChapterName());
        viewHolder2.imageView.setSelected(true);
        viewHolder2.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( !RetrofitClient.getSharedPrefsCookiePersistor().loadAll().isEmpty()) {
                    if (viewHolder2.imageView.isSelected()) {
                        RetrofitClient.getInstance().getService(WanAndroidService.class).deleteCollectArticle(datasBean.getId(), datasBean.getOriginId())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<BaseResponse<Articles>>() {
                                    @Override
                                    public void accept(BaseResponse<Articles> articleDetailBaseResponse) throws Throwable {
                                        dataData.remove(datasBean);
                                        notifyDataSetChanged();
                                    }
                                });
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataData == null ? 0 : dataData.size();
    }

    class CollectViewHolder extends RecyclerView.ViewHolder {
        TextView tv_author, tv_title, tv_type, tv_time, tv_shareUser;
        ImageView imageView;

        public CollectViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_author = itemView.findViewById(R.id.tv_author);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_shareUser = itemView.findViewById(R.id.tv_shareUser);
            imageView = itemView.findViewById(R.id.img_collect);
            imageView.setSelected(true);
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
            listener.onItemClick(dataData.get(position));
        }
    }


}
