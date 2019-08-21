package com.shi.test.androidmvplearningrecord.module.base;

/**
 * 实体类json解析，具体外层信息，具体改
 * author 施镇杰
 * email 452239676@qq.com
 * created 2019/8/21 下午6:27
 */

public class BaseResult<T> {
    private int code;
    private String msg;
    private T data;
    private String result;
    private String timestamp;

    public int getCode() {
        return code;
    }

    public BaseResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BaseResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public BaseResult setData(T data) {
        this.data = data;
        return this;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
               "code=" + code +
               ", msg='" + msg + '\'' +
               ", data=" + data +
               ", result='" + result + '\'' +
               ", timestamp='" + timestamp + '\'' +
               '}';
    }
}
