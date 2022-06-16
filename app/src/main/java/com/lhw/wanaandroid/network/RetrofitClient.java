package com.lhw.wanaandroid.network;

import android.content.Context;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.lhw.wanaandroid.util.MyApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static volatile RetrofitClient mInstance;
    public static String COOKIE;
    private Retrofit retrofit;
    private RetrofitClient() {

    }
    /**
     * 双重加锁
     *
     * @return
     */
    public static RetrofitClient getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitClient.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitClient();
                }
            }
        }
        return mInstance;
    }

    public <T> T getService(Class<T> cls) {
        return getRetrofit().create(cls);
    }

//    OkHttpClient okHttpClient = new OkHttpClient.Builder()
//            //链接超时
//            .connectTimeout(10, TimeUnit.SECONDS)
//            //读取超时
//            .readTimeout(10, TimeUnit.SECONDS)
//            //缓存
//
//            //添加Cookie拦截器
//            .addInterceptor(new SaveCookieInterceptor())
//            .addInterceptor(new LoadCookieInterceptor())
//            .build();

    private synchronized Retrofit getRetrofit() {
        if (retrofit == null) {
            ClearableCookieJar cookieJar =
                    new PersistentCookieJar(new SetCookieCache(),
                            new SharedPrefsCookiePersistor(MyApplication.getGloableContext()));
            OkHttpClient okHttpClient = new OkHttpClient.Builder().cookieJar(cookieJar).build();
            //创建Retrofit对象
            retrofit = new Retrofit.Builder()
                    .baseUrl(UrlConstainer.baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
