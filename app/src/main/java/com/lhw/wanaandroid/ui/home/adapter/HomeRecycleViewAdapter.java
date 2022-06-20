package com.lhw.wanaandroid.ui.home.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BannerData;
import com.lhw.wanaandroid.bean.BaseResponse;
import com.lhw.wanaandroid.login.LoginActivity;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.network.WanAndroidService;
import com.lhw.wanaandroid.util.ToastUtil;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    // 定义多布局
    //注意：如果有banner，banner必须为0
    private static final int TYPE_1 = 0;
    private static final int TYPE_2 = 1;
    private static final int TYPE_3 = 2;
    private RecyclerView recyclerView;
    private LayoutInflater layoutInflater;
    private List<ArticleDetail> dataData;
    private Context context;
    private List<BannerData> bannerData;
    private OnItemClickListener listener;
    private OnItemLongClickListener longClickListener;

    public HomeRecycleViewAdapter(RecyclerView recyclerView, Context context, List<ArticleDetail> dataData, List<BannerData> bannerData) {
        this.context = context;
        this.dataData = dataData;
        this.bannerData = bannerData;
        this.recyclerView = recyclerView;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setBannerData(List<BannerData> bannerData) {
        this.dataData.clear();
        this.bannerData.addAll(bannerData);
        notifyDataSetChanged();
    }
    public void setArticleData(List<ArticleDetail> dataData) {
//      this.articleList.clear();//上拉刷新会覆盖原来的
        this.dataData.addAll(dataData);
        notifyDataSetChanged();
    }
    public void setTopArticleData(List<ArticleDetail> dataData) {
//      this.articleList.clear();//上拉刷新会覆盖原来的
        this.dataData.addAll(dataData);
        notifyDataSetChanged();
    }

    //判断不同的条⽬布局
    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_1;}
//        }else if(position == 1){
//            return TYPE_2;
//        }
        else {
            return TYPE_2;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_1:
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_banner, parent, false);
                return new BannerViewHolder(view1);
//            case TYPE_2:
//                View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_item, parent, false);
//                return new ArticleTopViewHolder(view3);
            case TYPE_2:
                View view2 =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_item, parent, false);
                view2.setOnClickListener(this);
                view2.setOnLongClickListener(this);
                return new ArticleViewHolder(view2);
        }
        return null;
    }

    //绑定布局
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            //banner布局
            case TYPE_1:
                BannerViewHolder viewHolder1 = (BannerViewHolder) holder;
                //—————————————————————————如果你想偷懒，而又只是图片轮播————————————————————————
                viewHolder1.banner.setAdapter(new BannerImageAdapter<BannerData>(bannerData) {
                            @Override
                            public void onBindView(BannerImageHolder holder, BannerData data, int position, int size) {
                                //图片加载自己实现
                                Glide.with(holder.itemView)
                                        .load(data.getImagePath())
                                        .apply(RequestOptions.centerCropTransform())
                                        .into(holder.imageView);
                            }
                        })
                        .addBannerLifecycleObserver((LifecycleOwner) context)//添加生命周期观察者
                        .setIndicator(new CircleIndicator(this.context));
                break;
            //条⽬布局
//            case TYPE_2:
//                ArticleDetail datasBean1 = dataData.get(position-1);
//                ArticleTopViewHolder viewHolder3 = (ArticleTopViewHolder) holder;
//                viewHolder3.tv_title.setText(datasBean1.getTitle()+"");
//                viewHolder3.tv_shareUser.setText(datasBean1.getShareUser());
//                viewHolder3.tv_author.setText(datasBean1.getAuthor());
//                viewHolder3.tv_time.setText(datasBean1.getNiceDate());
//                viewHolder3.tv_type.setText(datasBean1.getSuperChapterName());
//                break;
            case TYPE_2:
                ArticleDetail datasBean = dataData.get(position - 1);
                ArticleViewHolder viewHolder2 = (ArticleViewHolder) holder;
                viewHolder2.tv_title.setText(datasBean.getTitle()+"");
                    viewHolder2.tv_shareUser.setText(datasBean.getShareUser());
                    viewHolder2.tv_author.setText(datasBean.getAuthor());
                viewHolder2.tv_time.setText(datasBean.getNiceDate());
                viewHolder2.tv_type.setText(datasBean.getSuperChapterName()+datasBean.getChapterName());

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
                            ToastUtil.showToast("请先登录");
                            context.startActivity(new Intent(context, LoginActivity.class));
                        }
                    }
                });
                break;
        }
    }
    //因为有banner所以总数量要 + 1
    @Override
    public int getItemCount() {
        return dataData == null ? 0: dataData.size() + 1;
    }
    class BannerViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public com.youth.banner.Banner banner;
        public BannerViewHolder(@NonNull View itemView) {

            super(itemView);
            this.rootView = itemView;
            this.banner = rootView.findViewById(R.id.banner);
        }
    }
    class ArticleTopViewHolder extends RecyclerView.ViewHolder{
        TextView tv_tag,tv_author,tv_title,tv_type,tv_time,tv_shareUser;
        ImageView imageView;
        public ArticleTopViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_author = itemView.findViewById(R.id.tv_author);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_shareUser = itemView.findViewById(R.id.tv_shareUser);
            imageView = itemView.findViewById(R.id.img_collect);

        }
    }
    class ArticleViewHolder extends RecyclerView.ViewHolder{
        TextView tv_tag,tv_author,tv_title,tv_type,tv_time,tv_shareUser;
        ImageView imageView;
        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_author = itemView.findViewById(R.id.tv_author);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_shareUser = itemView.findViewById(R.id.tv_shareUser);
            imageView = itemView.findViewById(R.id.img_collect);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.longClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            int position = recyclerView.getChildAdapterPosition(v);
            listener.onItemClick(dataData.get(position-1));
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (longClickListener != null){
            int position = recyclerView.getChildAdapterPosition(v);
            int id = dataData.get(position-1).getId();

            longClickListener.onItemLongClick(dataData.get(position-1),id);

        } return true;

    }
    public interface OnItemClickListener {
        void onItemClick(ArticleDetail datas);
    }
    public interface OnItemLongClickListener {
        void onItemLongClick(ArticleDetail datas,int id);

    }
}
