package com.shi.test.androidmvplearningrecord.http;

/**
 * Created by ${mty} on 2017/7/17.
 * http://blog.csdn.net/yunyuliunian
 */

public class ResultException extends RuntimeException {

    private int errCode = 0;

    public ResultException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}