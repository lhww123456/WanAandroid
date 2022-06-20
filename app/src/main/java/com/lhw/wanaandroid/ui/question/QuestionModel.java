package com.lhw.wanaandroid.ui.question;

import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BaseResponse;
import com.lhw.wanaandroid.network.RetrofitClient;
import com.lhw.wanaandroid.network.WanAndroidService;

import io.reactivex.rxjava3.core.Observable;

/**
 *
 * 功能描述：从网络获取数据
 */
public class QuestionModel implements QuestionContract.IQuestionModel {
    @Override
    public Observable<BaseResponse<Articles>> getQuestionData(int page) {
        return RetrofitClient.getInstance().getService(WanAndroidService.class)
                .getQuestionData(page);
    }
    //model层操作获取数据

}

