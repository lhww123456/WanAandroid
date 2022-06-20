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
    private static volatile ClearableCookieJar mcookieJar;
    private static volatile SharedPrefsCookiePersistor mSharedPrefsCookiePersistor;


    public static SharedPrefsCookiePersistor getSharedPrefsCookiePersistor() {
        if (mSharedPrefsCookiePersistor == null) {
            synchronized (RetrofitClient.class) {
                if (mSharedPrefsCookiePersistor == null) {
                    mSharedPrefsCookiePersistor = new SharedPrefsCookiePersistor(MyApplication.getGloableContext());
                }
            }
        }
        return mSharedPrefsCookiePersistor;
    }

    public static ClearableCookieJar getClearableCookieJar() {
        if (mcookieJar == null) {
            synchronized (RetrofitClient.class) {
                if (mcookieJar == null) {
                    mcookieJar = new PersistentCookieJar(new SetCookieCache(),
                            RetrofitClient.getSharedPrefsCookiePersistor());
                }
            }
        }

        return mcookieJar;
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

    private synchronized Retrofit getRetrofit() {
        if (retrofit == null) {
//            ClearableCookieJar cookieJar =
//                    new PersistentCookieJar(new SetCookieCache(),
//                            new SharedPrefsCookiePersistor(MyApplication.getGloableContext()));
//            OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                    .cookieJar(cookieJar).build();
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .cookieJar(RetrofitClient.getClearableCookieJar()).build();
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
