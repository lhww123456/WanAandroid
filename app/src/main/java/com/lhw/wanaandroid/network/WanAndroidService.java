package com.lhw.wanaandroid.network;


import com.lhw.wanaandroid.bean.Article;
import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BannerData;
import com.lhw.wanaandroid.bean.BaseBean;
import com.lhw.wanaandroid.bean.BaseResponse;
import com.lhw.wanaandroid.bean.Coin;
import com.lhw.wanaandroid.bean.CoinBean;
import com.lhw.wanaandroid.bean.NavCategoryBean;
import com.lhw.wanaandroid.bean.PageListData;
import com.lhw.wanaandroid.bean.ShareData;
import com.lhw.wanaandroid.bean.TreeData;
import com.lhw.wanaandroid.bean.UserData;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WanAndroidService {
    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     */
    @FormUrlEncoded
    @POST(UrlConstainer.LOGIN)
    Observable<BaseResponse<UserData>> login(@Field("username") String username, @Field("password") String password);

    /**
     * 注册
     * @param username   用户名
     * @param password   密码
     * @param repassword 重复密码
     */
    @FormUrlEncoded
    @POST(UrlConstainer.REGISTER)
    Observable<BaseResponse<UserData>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    //退出登录
    @GET("user/logout/json")
    Observable<BaseResponse> logout();

    /**
     * 轮播图
     */
    @GET(UrlConstainer.MAIN_BANNER)
    Observable<BaseResponse<List<BannerData>>> getBanner();

    /**
     * 首页置顶列表
     */
    @GET(UrlConstainer.HOME_TOP_LIST)
    Observable<BaseResponse<Articles>> getHomeTopList();

    /**
     * 首页文章列表
     */
    @GET(UrlConstainer.HOME_LIST)
    Observable<BaseResponse<Articles>> getArticleData(@Path("page") int page);

    /**
     * 收藏文章
     */
    @POST(UrlConstainer.COLLECT_ARTICLE)
    Observable<BaseResponse<Articles>> collectArticle(@Path("id") int id);

    /**
     * 取消收藏文章
     */
    @POST(UrlConstainer.UNCOLLECT_ARTICLE)
    Observable<BaseResponse<Articles>> unCollectArticle(@Path("id") int id);

    //问答
    @GET(UrlConstainer.WENDA)
    Observable<BaseResponse<Articles>> getQuestionData(@Path("page") int page);

    /**
     * 知识体系分类
     */
    @GET(UrlConstainer.TREE)
    Observable<BaseResponse<List<TreeData>>> getTree();

    /**
     * 知识体系列表
     * @param cid
     * @param page
     */
    @GET(UrlConstainer.TREE_LIST)
    Observable<BaseResponse<Articles>> getTreeList(@Path("page") int page, @Query("cid") int cid);

    /**
     * 收藏的文章列表
     * @param page
     * @return
     */
    @GET(UrlConstainer.COLLECT_ARTICLE_LIST)
    Observable<BaseResponse<Articles>> getCollectArticleList(@Path("page") int page);

    /**
     * 删除收藏的文章
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST(UrlConstainer.DELETE_COLLECT_ARTICLE)
    Observable<BaseResponse<Articles>> deleteCollectArticle(@Path("id") int page, @Field("originId") int originId);

    /**
     * 获取个人积分，需要登录后访问
     */
    @GET(UrlConstainer.COIN)
    Observable<BaseResponse<Coin>> getMyCoinCount();

    /**
     * 获取个人积分获取列表
     * page 1开始
     * 需要登录后访问
     */
    @GET(UrlConstainer.COINLIST)
    Observable<BaseResponse<CoinBean>> getMyCoinList(@Path("page") int page);

    //分享文章
    @POST(UrlConstainer.SHAREARTICLE)
    @FormUrlEncoded
    Observable<BaseResponse> shareArticle(@Field("title")String title, @Field("link")String link);

    //分享文章列表
    @GET(UrlConstainer.SHAREARTICLELIST)
    Observable<BaseResponse<ShareData>> shareArticleList(@Path("page") int page);

    // 常用网站
    @GET(UrlConstainer.NAVI)
    Observable<BaseResponse<List<NavCategoryBean>>> getNavCategoryData();

    /**
     * 搜索文章
     *
     * @param page
     * @param keyword
     * @return
     */
    @FormUrlEncoded
    @POST(UrlConstainer.SEARCH)
    Observable<BaseBean<PageListData<Article>>> search(@Path("page") int page, @Field("k") String keyword);

}
