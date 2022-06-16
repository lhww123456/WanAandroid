package com.lhw.wanaandroid.network;

import android.text.TextUtils;

import com.lhw.wanaandroid.util.PreUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加Cookie
 */

public class LoadCookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

//        String mCookieStr = (String) PreUtils.get(chain.request().url().host(), "");
        //添加cookie
        if (!TextUtils.isEmpty(RetrofitClient.COOKIE)) {
            builder.addHeader("Cookie",
//                    mCookieStr.substring(0, mCookieStr.length() - 1));//长度减1为了去除最后的逗号 builder.addHeader("Cookie",
                    RetrofitClient.COOKIE);
        }
        return chain.proceed(builder.build());
    }
}









