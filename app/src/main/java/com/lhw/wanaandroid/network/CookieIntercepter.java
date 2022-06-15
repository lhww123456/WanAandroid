//package com.lhw.wanaandroid.network;
//
//import java.io.IOException;
//
//import okhttp3.Interceptor;
//import okhttp3.Response;
//
///**
// * 在登录时获取
// */
//public class CookieIntercepter implements Interceptor {
//    public CookieIntercepter() {
//        super();
//    }
//
//    @Override
//    public Response intercept(Interceptor.Chain chain) throws IOException {
//        //执行请求获取服务器返回的响应
//        Response response = chain.proceed(chain.request());
//        if(!response.headers("Set-Cookie").isEmpty()){
//            //解析Cookie
//            for (String header : response.headers("Set-Cookie")) {
//                if(header.contains("JSESSIONID")){
//                    String cookie = header.substring(header.indexOf("JSESSIONID"), header.indexOf(";"));
//                    SPUtils.getInstance().saveInfo("cookie",cookie);
//                }
//            }
//        }
//        return response;
//    }
//}
//
