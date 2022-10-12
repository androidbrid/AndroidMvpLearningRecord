package com.shi.test.androidmvplearningrecord.http;

import androidx.annotation.NonNull;
import android.util.Log;
import com.orhanobut.logger.Logger;
import com.shi.test.androidmvplearningrecord.MyApplication;
import com.shi.test.androidmvplearningrecord.http.gson.CustomConverterFactory;
import com.shi.test.androidmvplearningrecord.utils.NetUtil;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * 网络请求基类
 * author 施镇杰
 * email 452239676@qq.com
 * created 2019/8/21 上午10:14
 */
public class HttpMethods {

    private static final int DEFAULT_TIMEOUT = 10;//默认超时时间
    protected static HttpMethods instance = null; //单例
    private Retrofit retrofit;
    private HttpApi welfareHttpApi;
    //设缓存有效期为1天
    static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置
    //(假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)
    static final String CACHE_CONTROL_NETWORK = "Cache-Control: public, max-age=3600";
    // 避免出现 HTTP 403 Forbidden，参考：http://stackoverflow.com/questions/13670692/403-forbidden-with-java-but-not-web-browser
    static final String AVOID_HTTP403_FORBIDDEN = "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";

    private HttpMethods(boolean isToken) {
        // 指定缓存路径,缓存大小100Mb
        Cache cache = new Cache(new File(MyApplication.getContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        HttpLoggingInterceptor loggingInterceptor = new
                HttpLoggingInterceptor(message -> Log.d("header", message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache)
                                                              .retryOnConnectionFailure(true)
                                                              .addInterceptor(loggingInterceptor)
                                                              .addInterceptor(getHeaderInterceptor(isToken))
                                                              .addInterceptor(sRewriteCacheControlInterceptor)
                                                              .addNetworkInterceptor(sRewriteCacheControlInterceptor)
                                                              .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                                                              .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(CustomConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(HttpApi.WELFARE_HOST)
                .build();
        welfareHttpApi = retrofit.create(HttpApi.class);
    }


    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private static final Interceptor sRewriteCacheControlInterceptor = chain -> {
        Request request = chain.request();
        if (!NetUtil.isNetworkAvailable(MyApplication.getContext())) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            Logger.e("no network");
        }
        Response originalResponse = chain.proceed(request);

        if (NetUtil.isNetworkAvailable(MyApplication.getContext())) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder()
                                   .header("Cache-Control", cacheControl)
                                   .removeHeader("Pragma")
                                   .build();
        } else {
            return originalResponse.newBuilder()
                                   .header("Cache-Control", "public, " + CACHE_CONTROL_CACHE)
                                   .removeHeader("Pragma")
                                   .build();
        }
    };

    /**
     * 拦截器添加请求头
     */
    public static Interceptor getHeaderInterceptor(final boolean isToken) {
        return chain -> {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder()
                                                            .addHeader("Accept-Encoding", "gzip")
                                                            .addHeader("Accept", "application/json")
                                                            .addHeader("Content-Type", "application/json; charset=utf-8")
                                                            .method(originalRequest.method(), originalRequest.body());
            if(isToken){
                requestBuilder.addHeader("Authorization", "Bearer " );//添加请求头信息，服务器进行token有效性验证
            }
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
    }


    public HttpApi getWelfareHttpApi() {
        return welfareHttpApi;
    }


    public static HttpMethods getInstance(boolean isToken) {
        if (instance == null) {
            synchronized (HttpMethods.class) {
                if (instance == null) {
                    instance = new HttpMethods(isToken);
                }
            }
        }
        return instance;
    }

}
