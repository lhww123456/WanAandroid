package com.lhw.wanaandroid.ui.question;

import android.util.Log;


import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BaseResponse;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public  class QuestionPresenter implements QuestionContract.IQuestionPresenter {
    private final QuestionContract.IQuestionView questionView;
    private QuestionContract.IQuestionModel questionModel;

    public QuestionPresenter(QuestionContract.IQuestionView questionView){
        this.questionView = questionView;
        questionModel = new QuestionModel();
    }


    public void getQuestionData(int page) {
        questionModel.getQuestionData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse<Articles>>() {
                    @Override
                    public void accept(BaseResponse<Articles> questionBaseBean) throws Throwable {
                        Articles data = questionBaseBean.getData();
                        List<ArticleDetail> datas = data.getDatas();
//                        Log.e("lhww_question", "accept: "+data );
//                        Log.e("lhww_question", "accept: "+datas );
                        questionView.getQuestionSuccess(datas);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.e("TAG", "accept: "+throwable.getLocalizedMessage() );
                        questionView.getFailure(throwable);
                    }
                })

        ;
    }
}
