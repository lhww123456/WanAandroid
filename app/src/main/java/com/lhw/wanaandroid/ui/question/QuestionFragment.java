package com.lhw.wanaandroid.ui.question;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lhw.wanaandroid.R;
import com.lhw.wanaandroid.WebViewActivity;
import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.ui.base.BaseFragment;
import com.lhw.wanaandroid.ui.question.adapter.QuestionRecycleViewAdapter;
import com.lhw.wanaandroid.util.ToastUtil;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragment extends BaseFragment implements QuestionContract.IQuestionView,QuestionRecycleViewAdapter.OnItemClickListener{
    private RecyclerView recyclerView1;
    private RefreshLayout refreshLayout;
    private QuestionPresenter questionPresenter;
    private int page = 0;//从第0页开始
    private QuestionRecycleViewAdapter questionRecycleViewAdapter;
    private List<ArticleDetail> questions;
    private  ProgressDialog progressDialog;
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_question;
    }

    @Override
    protected void initSubViews() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("正在努力为您加载···");
        progressDialog.show();

        refreshLayout = (RefreshLayout)find(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                progressDialog.setMessage("正在努力为您加载···");
                progressDialog.show();
                questions.clear();
                int page1 = 0;
                questionPresenter.getQuestionData(page1);
                refreshlayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
                progressDialog.dismiss();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page += 1;
                questionPresenter.getQuestionData(page);
                refreshlayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
            }
        });


        recyclerView1 = find(R.id.question_recycleView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        questions = new ArrayList<>();

        recyclerView1.setLayoutManager(linearLayoutManager);

        questionRecycleViewAdapter = new QuestionRecycleViewAdapter(recyclerView1,getContext(), questions);
        questionRecycleViewAdapter.setOnItemClickListener(this);

        recyclerView1.setAdapter(questionRecycleViewAdapter);

        questionPresenter = new QuestionPresenter(this);
        questionPresenter.getQuestionData(page);

    }


    @Override
    public void getQuestionSuccess(List<ArticleDetail> datas) {
        progressDialog.dismiss();
        questionRecycleViewAdapter.setQuestionData(datas);
    }

    @Override
    public void getFailure(Throwable throwable) {
        progressDialog.dismiss();
        ToastUtil.showToast("遇到了问题");
    }

    @Override
    public void onItemClick(ArticleDetail datas) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        Log.e("lhww 问答页面点击", "onItemClick: 我点了"+ datas.toString());
        intent.putExtra(WebViewActivity.WEB_URL,datas.getLink());
        intent.putExtra("titile",datas.getTitle());
        startActivity(intent);
    }

}
