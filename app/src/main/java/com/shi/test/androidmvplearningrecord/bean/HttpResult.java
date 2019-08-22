package com.shi.test.androidmvplearningrecord.bean;

public class HttpResult<T> {

    /**
     * 实体类json解析，具体外层信息，具体改
     * author 施镇杰
     * email 452239676@qq.com
     * created 2019/8/21 下午6:27
     */
    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
    @Override
    public String toString() {
        return "HttpResult{" +
               "error=" + error +
               ", results=" + results +
               '}';
    }
}
