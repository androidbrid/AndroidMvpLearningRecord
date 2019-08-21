package com.shi.test.androidmvplearningrecord.http;

import com.shi.test.androidmvplearningrecord.bean.WelfarePhotoList;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
  *
  *网络请求地址
  *author 施镇杰
  *email 452239676@qq.com
  *created 2019/8/21 上午10:36
  */
public interface HttpApi {

    String WELFARE_HOST = "http://gank.io/";

    /**
     * 获取福利图片
     * eg: http://gank.io/api/data/福利/10/1
     *
     * @param page 页码
     * @return
     */
    @Headers(HttpMethods.CACHE_CONTROL_NETWORK)
    @GET("/api/data/福利/10/{page}")
    Observable<WelfarePhotoList> getWelfarePhoto(@Path("page") int page);
}
