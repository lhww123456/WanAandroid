package com.lhw.wanaandroid.network;

import android.util.Log;

import com.lhw.wanaandroid.util.PreUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 获取，保存Cookie
 */

public class SaveCookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        List<String> mCookieList = response.headers("Set-Cookie");
        //解析cookie
        if (!mCookieList.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String cookie : mCookieList) {
                //注意Cookie请求头字段中的每个Cookie之间用逗号或分号分隔

                sb.append(cookie).append(",");

            }
            //保存Cookie
//            PreUtils.put(response.request().url().host(), sb.toString());
            Log.e(SaveCookieInterceptor.class.getSimpleName(), "intercept: url : " + request.url());
        }
        return response;
    }
}
