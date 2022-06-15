//package com.lhw.wanaandroid.network;
//
//import java.io.IOException;
//
//import okhttp3.Interceptor;
//import okhttp3.Request;
//import okhttp3.Response;
//
///**
// * 将Cookie添加到请求的头部(在收藏时使用)
// */
//public class AddCookieToHeader implements Interceptor {
//    public AddCookieToHeader() {
//        super();
//    }
//
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request.Builder request = chain.request().newBuilder();
//        String cookie = SPUtils.getInstance().getInfo("cookie", null);
//        if(cookie != null) {
//                request.addHeader("Cookie", cookie);
//            }
//        return chain.proceed(request.build());
//        }
//}
