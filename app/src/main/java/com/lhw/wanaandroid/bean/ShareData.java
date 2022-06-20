package com.lhw.wanaandroid.bean;

public class ShareData {
    /**
     * {
     *     "data": {
     *         "coinInfo": {
     *             "coinCount": 218,
     *             "level": 3,
     *             "nickname": "",
     *             "rank": "12149",
     *             "userId": 132981,
     *             "username": "w**g8881"
     *         },
     *         "shareArticles": {
     *             "curPage": 1,
     *             "datas": [
     *                 {
     *                     "apkLink": "",
     *                     "audit": 0,
     *                     "author": "",
     *                     "canEdit": false,
     *                     "chapterId": 494,
     *                     "chapterName": "广场",
     *                     "collect": true,
     *                     "courseId": 13,
     *                     "desc": "",
     *                     "descMd": "",
     *                     "envelopePic": "",
     *                     "fresh": true,
     *                     "host": "",
     *                     "id": 23084,
     *                     "link": "https://lhzyk.lanzouq.com/iPXrt05s9o9g?w1",
     *                     "niceDate": "1天前",
     *                     "niceShareDate": "1天前",
     *                     "origin": "",
     *                     "prefix": "",
     *                     "projectLink": "",
     *                     "publishTime": 1655446633000,
     *                     "realSuperChapterId": 493,
     *                     "selfVisible": 0,
     *                     "shareDate": 1655446633000,
     *                     "shareUser": "wang8881",
     *                     "superChapterId": 494,
     *                     "superChapterName": "广场Tab",
     *                     "tags": [],
     *                     "title": "111",
     *                     "type": 0,
     *                     "userId": 132981,
     *                     "visible": 0,
     *                     "zan": 0
     *                 }
     *             ],
     *             "offset": 0,
     *             "over": true,
     *             "pageCount": 1,
     *             "size": 20,
     *             "total": 1
     *         }
     *     },
     *     "errorCode": 0,
     *     "errorMsg": ""
     * }
     */

    private CoinInfo coinInfo;
    private Articles shareArticles;

    public CoinInfo getCoinInfo() {
        return coinInfo;
    }

    public void setCoinInfo(CoinInfo coinInfo) {
        this.coinInfo = coinInfo;
    }

    public Articles getShareArticles() {
        return shareArticles;
    }

    public void setShareArticles(Articles shareArticles) {
        this.shareArticles = shareArticles;
    }

    @Override
    public String toString() {
        return "ShareData{" +
                "coinInfo=" + coinInfo +
                ", shareArticles=" + shareArticles +
                '}';
    }
}
