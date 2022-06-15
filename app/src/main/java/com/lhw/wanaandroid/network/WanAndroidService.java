package com.lhw.wanaandroid.network;


import com.lhw.wanaandroid.bean.ArticleDetail;
import com.lhw.wanaandroid.bean.Articles;
import com.lhw.wanaandroid.bean.BannerData;
import com.lhw.wanaandroid.bean.BaseBean;
import com.lhw.wanaandroid.bean.NavCategoryBean;
import com.lhw.wanaandroid.bean.TreeData;
import com.lhw.wanaandroid.login.bean.BaseResponse;

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
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @FormUrlEncoded
    @POST(UrlConstainer.LOGIN)
    Observable<BaseResponse> login(@Field("username") String username, @Field("password") String password);

    /**
     * 注册
     *
     * @param username   用户名
     * @param password   密码
     * @param repassword 重复密码
     * @return
     */
    @FormUrlEncoded
    @POST(UrlConstainer.REGISTER)
    Observable<BaseResponse> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    @GET("user/logout/json")
    Observable<BaseResponse> logout();
    /**
     * 轮播图
     *
     * @return
     */
    @GET(UrlConstainer.MAIN_BANNER)
    Observable<BaseBean<List<BannerData>>> getBanner();

    /**
     * 首页置顶列表
     * @return
     */
    @GET(UrlConstainer.HOME_TOP_LIST)
    Observable<BaseBean<List<ArticleDetail>>> getHomeTopList();

    /**
     * 首页文章列表
     *
     * @return
     */
    @GET(UrlConstainer.HOME_LIST)
    Observable<BaseBean<Articles>> getArticleData(@Path("page") int page);
    /**
     * 收藏文章
     *
     * @param id
     * @return
     */
    @POST(UrlConstainer.COLLECT_ARTICLE)
    Observable<BaseBean<String>> collectArticle(@Path("id") int id);

    /**
     * 取消收藏文章
     *
     * @param id
     * @return
     */

    @POST(UrlConstainer.UNCOLLECT_ARTICLE)
    Observable<BaseBean<String>> unCollectArticle(@Path("id") int id);

    @GET("wenda/list/{page}/json ")
    Observable<BaseBean<Articles>> getQuestionData(@Path("page") int page);
    /**
     * 知识体系分类
     *
     * @return
     */
    @GET(UrlConstainer.TREE)
    Observable<BaseBean<List<TreeData>>> getTree();

    /**
     * 知识体系列表
     *
     * @param cid
     * @param page
     * @return
     */
    @GET(UrlConstainer.TREE_LIST)
    Observable<BaseBean<Articles>> getTreeList(@Path("page") int page, @Query("cid") int cid);

//
//    /**
//     * 收藏的文章列表
//     *
//     * @param page
//     * @return
//     */
//    @GET(UrlConstainer.COLLECT_ARTICLE_LIST)
//    Observable<BaseBean<PageListData<Article>>> getCollectArticleList(@Path("page") int page);
//
//    /**
//     * 删除收藏的文章
//     *
//     * @param id
//     * @return
//     */
//    @FormUrlEncoded
//    @POST(UrlConstainer.DELETE_COLLECT_ARTICLE)
//    Observable<BaseBean<String>> deleteCollectArticle(@Path("id") int id, @Field("originId") int originId);
//
//    /**
//     * 搜索文章
//     *
//     * @param page
//     * @param keyword
//     * @return
//     */
//    @FormUrlEncoded
//    @POST(UrlConstainer.SEARCH)
//    Observable<BaseBean<PageListData<Article>>> search(@Path("page") int page, @Field("k") String keyword);
//
//    /**
//     * 搜索热词
//     *
//     * @return
//     */
//    @GET(UrlConstainer.HOT_KEYWORD)
//    Observable<BaseBean<List<Hotword>>> getHotKeyword();
//
//    /**
//     * 常用网站
//     *
//     * @return
//     */
//    @GET(UrlConstainer.FRIEND)
//    Observable<BaseBean<List<Friend>>> getFriend();

    @GET("navi/json")
    Observable<BaseBean<List<NavCategoryBean>>> getNavCategoryData();
}
