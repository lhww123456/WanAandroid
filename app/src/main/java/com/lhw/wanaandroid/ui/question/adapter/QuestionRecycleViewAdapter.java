package com.lhw.wanaandroid.ui.question.adapter;


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

public class QuestionRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private  RecyclerView recyclerView;
    private  Context context;
    private  List<ArticleDetail> questionlist;
    private OnItemClickListener mListener;

    public QuestionRecycleViewAdapter(RecyclerView recyclerView,Context context, List<ArticleDetail> question) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.questionlist = question;
    }

    public void setQuestionData(List<ArticleDetail> data) {
//        this.questionlist.clear();
        this.questionlist.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_item, parent, false);
        view2.setOnClickListener(this);
        return new QuestionViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ArticleDetail datasBean = questionlist.get(position);
        QuestionViewHolder viewHolder2 = (QuestionViewHolder) holder;
        viewHolder2.tv_title.setText(datasBean.getTitle());
        viewHolder2.tv_shareUser.setText(datasBean.getShareUser());
        viewHolder2.tv_author.setText(datasBean.getAuthor());
        viewHolder2.tv_time.setText(datasBean.getNiceDate());
        viewHolder2.tv_type.setText(datasBean.getSuperChapterName());

        viewHolder2.imageView.setSelected(datasBean.getCollect());
        viewHolder2.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!RetrofitClient.getSharedPrefsCookiePersistor().loadAll().isEmpty()) {
                    if (viewHolder2.imageView.isSelected()) {
                        RetrofitClient.getInstance().getService(WanAndroidService.class).unCollectArticle(datasBean.getId())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<BaseResponse<Articles>>() {
                                    @Override
                                    public void accept(BaseResponse<Articles> articlesBaseResponse) throws Throwable {
                                        ToastUtil.showToast("已取消收藏");

                                        viewHolder2.imageView.setSelected(false);
                                    }
                                });
                    } else {
                        RetrofitClient.getInstance().getService(WanAndroidService.class).collectArticle(datasBean.getId())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<BaseResponse<Articles>>() {
                                    @Override
                                    public void accept(BaseResponse<Articles> articlesBaseResponse) throws Throwable {
                                        ToastUtil.showToast("已收藏");

                                        viewHolder2.imageView.setSelected(true);
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
        return questionlist.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView tv_author, tv_title, tv_type, tv_time, tv_shareUser;
        ImageView imageView;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_author = itemView.findViewById(R.id.tv_author);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_shareUser = itemView.findViewById(R.id.tv_shareUser);
            imageView = itemView.findViewById(R.id.img_collect);

        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }
    @Override
    public void onClick(View v) {
        if (mListener != null){
            int position = recyclerView.getChildAdapterPosition(v);
            mListener.onItemClick(questionlist.get(position));
        }
    }
    public interface OnItemClickListener {
        //问答点击事件
        void onItemClick(ArticleDetail datas);

    }
}

