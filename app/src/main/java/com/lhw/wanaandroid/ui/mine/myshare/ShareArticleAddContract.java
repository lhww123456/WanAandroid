package com.lhw.wanaandroid.ui.mine.myshare;

public interface ShareArticleAddContract {

    interface  IView {
        void shareAddSuccess();
        void shareAddFailure();
    }

    interface IPresenter {
        void shareAdd(String title, String link);
    }

    interface  IModel {
        void shareAdd(String title, String link);
    }
}
