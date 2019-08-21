package com.shi.test.androidmvplearningrecord.module.base;

/**
  *
  *请求通用回调接口
  *author 施镇杰
  *email 452239676@qq.com
  *created 2019/8/21 下午6:28
  */

public interface OnBaseListener<T> {

    /**
     * 请求成功时回调
     *
     * @param result
     */
    void onSuccess(T result);

    /**
     * 请求失败时回调
     *
     * @param error
     */
    void onError(int code, String error);


    /***
     * 请求处理完成
     */
    void  onCompleted();
}
