package com.shi.test.androidmvplearningrecord.http;

/**
 * Created by ${mty} on 2017/7/17.
 * http://blog.csdn.net/yunyuliunian
 */

public class NOPAYPASSException extends RuntimeException {

    private int errCode = 0;

    public NOPAYPASSException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}