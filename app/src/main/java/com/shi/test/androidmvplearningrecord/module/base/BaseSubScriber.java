package com.shi.test.androidmvplearningrecord.module.base;

import android.util.Log;
import com.google.gson.Gson;
import com.shi.test.androidmvplearningrecord.bean.HttpResult;
import com.shi.test.androidmvplearningrecord.http.*;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.HttpException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 请求通用回调
 * 每个请求都要做的事先到这里做  然后调用监听
 * author 施镇杰
 * email 452239676@qq.com
 * created 2019/8/22 下午3:27
 */

public class BaseSubScriber implements Observer<HttpResult> {

    private OnBaseListener listener;

    public BaseSubScriber(OnBaseListener listener) {
        this.listener = listener;
    }

    @Override
    public void onError(Throwable e) {
        Log.e("error:", e.toString());
        if (e instanceof Exception) {
            //访问获得对应的Exception
            ExceptionHandle.ResponeThrowable responseThrowable = ExceptionHandle.handleException(e);
            listener.onError(responseThrowable.code, responseThrowable.message);
        } else {
            //将Throwable 和 未知错误的status code返回
            ExceptionHandle.ResponeThrowable responseThrowable = new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN);
            listener.onError(responseThrowable.code, responseThrowable.message);
        }
    }

    @Override
    public void onComplete() {
        listener.onCompleted();
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(HttpResult o) {
        listener.onSuccess(o);
    }
}
