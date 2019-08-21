package com.shi.test.androidmvplearningrecord.bean;

public class HttpResult<T> {

    /**
     * error : false
     */

    private boolean error;
    private T params;
    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}
