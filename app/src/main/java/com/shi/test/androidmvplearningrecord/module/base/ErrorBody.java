package com.shi.test.androidmvplearningrecord.module.base;

/**
  *
  *服务器错误信息返回
  *author 施镇杰
  *email 452239676@qq.com
  *created 2019/8/21 下午6:26
  */

public class ErrorBody<T> {

    /**
     * code : 4035
     * result : Invalid Token
     * msg : token失效
     * timestamp : 1523416312
     * date : 2018-04-11 11:11:52
     * ip : 115.196.133.23
     * data : []
     */

    private int code;
    private String result;
    private String msg;
    private int timestamp;
    private String date;
    private String ip;
    private T data;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() { return code;}

    public void setCode(int code) { this.code = code;}

    public String getResult() { return result;}

    public void setResult(String result) { this.result = result;}

    public String getMsg() { return msg;}

    public void setMsg(String msg) { this.msg = msg;}

    public int getTimestamp() { return timestamp;}

    public void setTimestamp(int timestamp) { this.timestamp = timestamp;}

    public String getDate() { return date;}

    public void setDate(String date) { this.date = date;}

    public String getIp() { return ip;}

    public void setIp(String ip) { this.ip = ip;}


    @Override
    public String toString() {
        return "ErrorBody{" +
               "code=" + code +
               ", result='" + result + '\'' +
               ", msg='" + msg + '\'' +
               ", timestamp=" + timestamp +
               ", date='" + date + '\'' +
               ", ip='" + ip + '\'' +
               '}';
    }
}
