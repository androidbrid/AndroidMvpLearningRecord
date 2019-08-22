package com.shi.test.androidmvplearningrecord.http;

import com.shi.test.androidmvplearningrecord.bean.HttpResult;
import com.shi.test.androidmvplearningrecord.bean.WelfarePhotoInfo;
import com.shi.test.androidmvplearningrecord.bean.WelfarePhotoList;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

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
    Observable<HttpResult<List<WelfarePhotoInfo>>> getWelfarePhoto(@Path("page") int page);
}
