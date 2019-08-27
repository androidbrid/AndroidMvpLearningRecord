package com.shi.test.androidmvplearningrecord.http;

import android.util.Log;
import com.google.gson.JsonParseException;
import org.json.JSONException;
import retrofit2.adapter.rxjava2.HttpException;

import java.net.ConnectException;

/**
 * RxJava+Retrofit 网络异常捕获、状态码统一处理
 * author 施镇杰
 * email 452239676@qq.com
 * created 2019/8/27 下午4:13
 */
public class ExceptionHandle {
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ResponeThrowable handleException(Throwable e) {
        ResponeThrowable ex;
        Log.i("tag", "e.toString = " + e.toString());
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ResponeThrowable(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    ex.message = "没有提供认证信息";
                    break;
                case FORBIDDEN:
                    ex.message = "请求的资源不允许访问";
                    break;
                case NOT_FOUND:
                    ex.message = "请求的内容不存在";
                    break;
                case REQUEST_TIMEOUT:
                    ex.message = "客户端请求超时";
                    break;
                case GATEWAY_TIMEOUT:
                    ex.message = "网关超时";
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.message = "服务器错误";
                    break;
                case BAD_GATEWAY:
                    ex.message = "网关错误";
                    break;
                case SERVICE_UNAVAILABLE:
                    ex.message = "服务暂时不可用";
                    break;
                default:
                    //ex.code = httpException.code();
                    ex.message = "网络错误";
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            ex = new ResponeThrowable(resultException, resultException.code);
            ex.message = resultException.message;
            return ex;
        } else if (e instanceof JsonParseException
                   || e instanceof JSONException
            /*|| e instanceof ParseException*/) {
            ex = new ResponeThrowable(e, ERROR.PARSE_ERROR);
            ex.message = "解析错误";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ResponeThrowable(e, ERROR.NETWORD_ERROR);
            ex.message = "连接失败";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ResponeThrowable(e, ERROR.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex;
        } else {
            ex = new ResponeThrowable(e, ERROR.UNKNOWN);
            ex.message = "未知错误";
            return ex;
        }
    }


    /**
     * 约定异常
     */
    public static class ERROR {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;
    }

    public static class ResponeThrowable extends Exception {
        public int code;
        public String message;

        public ResponeThrowable(Throwable throwable, int code) {
            super(throwable);
            this.code = code;
        }
    }

    /**
     * ServerException发生后，将自动转换为ResponeThrowable返回
     */
    class ServerException extends RuntimeException {
        int code;
        String message;
    }
}
