package com.shi.test.androidmvplearningrecord.module.base;

import android.util.Log;
import com.google.gson.Gson;
import com.shi.test.androidmvplearningrecord.bean.HttpResult;
import com.shi.test.androidmvplearningrecord.http.NOPAYPASSException;
import com.shi.test.androidmvplearningrecord.http.NeedProveException;
import com.shi.test.androidmvplearningrecord.http.NoReturnException;
import com.shi.test.androidmvplearningrecord.http.ResultException;
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

    private String msg = "";
    private OnBaseListener listener;

    public BaseSubScriber(OnBaseListener listener) {
        this.listener = listener;
    }

    @Override
    public void onError(Throwable e) {
        Log.e("error:", e.toString());
        try {
            if (e.getMessage() != null)
                Log.e("error:", e.getMessage());
            if (e instanceof NoReturnException) {
                //空请求  不打印 但是走失败方法
                listener.onError(1000, "");
            } else if (e instanceof HttpException) {
                ResponseBody body = ((HttpException) e).response().errorBody();
                ErrorBody errorBody = null;
                try {
                    errorBody = new Gson().fromJson(body.string(), ErrorBody.class);
                    Log.e("errorBody:", errorBody.getMsg());
                } catch (Exception e1) {

                }
                if (errorBody != null) {
                    if (errorBody.getCode() == 4035) {
                        //App.getInstance().TokenInvalid();//重新登录
                        listener.onError(4035, "重新登录!");
                        //EventBus.getDefault().post("重新登录");
                    }
                    if (errorBody.getCode() == 4036) {
                        //App.getInstance().TokenInvalid();//重新登录
                        //listener.onError(4036, "重新登录!");
                        //EventBus.getDefault().post("重新登录");
                    } else if (errorBody.getCode() == 40413) {
                        listener.onError(40413, new Gson().toJson(errorBody.getData()));
                    } else {
                        listener.onError(1000, errorBody.getMsg());
                    }
                } else {
                    listener.onError(1000, e.getMessage());
                }
            } else if (e instanceof UnknownHostException) {
                listener.onError(1000, "连接不到服务器，请检查当前网络环境！");
            } else if (e instanceof SocketTimeoutException) {
                listener.onError(1000, "网络连接超时，请稍后重试！");
            } else if (e instanceof NeedProveException) {
                //EventBus.getDefault().post(AppConst.NEEDPROVE);
                listener.onError(206, e.getMessage());
            } else if (e instanceof NOPAYPASSException) {
                listener.onError(207, e.getMessage());
            } else if (e instanceof ResultException) {
                if (((ResultException) e).getErrCode() == 320) {
                    //App.getInstance().setUser(new User());
                    //App.getInstance().loginout();
                    //EventBus.getDefault().post(new User());
                    //EventBus.getDefault().post(AppConst.TOLOGIN);
                    listener.onError(403, "需要重新登录!");
                } else {
                    msg = e.getMessage();
                    if (msg.contains("[")) {
                        msg = msg.replace("[", "").replace("]", "");
                    }
                    if (msg.contains("{")) {
                        msg = msg.replace("{", "").replace("}", "");
                    }
                    if (msg.trim().equals("")) {
                        //if (App.getInstance().getUser().getUin() != null && !""
                        //        .equals(App.getInstance().getUser().getUin())) {
                        //    msg = "服务器异常，请稍后重试！";
                        //} else {
                        //    msg = "需要重新登录！";
                        //}
                    }
                    listener.onError(1000, msg);
                }
            } else if (e instanceof NullPointerException) {
                listener.onError(1000, "服务器异常，请稍后重试！");
            } else if (e.getMessage() != null && e.getMessage().contains("Failed to connect")) {
                listener.onError(1000, "网络连接超时，请稍后重试！");
            } else if (e.getMessage() != null && e.getMessage().contains("failed to connect")) {
                listener.onError(1000, "网络连接超时，请稍后重试！");
            } else if (e.getMessage() != null && e.getMessage().contains("timeout")) {
                listener.onError(1000, "网络连接超时，请稍后重试！");
            } else if (e.getMessage() != null && e.getMessage()
                                                  .contains("No address associated with hostname")) { //服务器错误
                listener.onError(1000, "没有可用的网络，请检查当前网络环境！");
            } else if (e.getMessage() != null && e.getMessage().contains("HTTP 500 Internal Server Error")) {
                listener.onError(1000, "服务器异常，请稍后重试！");
            } else {
                listener.onError(1000, e.getMessage());
            }
        } catch (IllegalStateException e1) {
            //空错误
            Log.e("BaseSubScriber错误", e1.getMessage());
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
