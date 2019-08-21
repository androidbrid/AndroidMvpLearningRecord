package com.shi.test.androidmvplearningrecord.http.gson;


import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.shi.test.androidmvplearningrecord.module.base.BaseResult;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio
 * Package: com.example.administrator.mytest.TestForHttp
 * Date:    2016-06-01
 * Time:    14:13
 * 类描述：
 */
final class CustomResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        String body = value.string();
        Log.d("body:", body);
        BaseResult resultResponse = gson.fromJson(body, BaseResult.class);
        if (resultResponse == null) {
            throw new NoReturnException(1000, "");//空返回
        } else {
            if ("OK".equals(resultResponse.getResult()) || resultResponse.getCode() == 200 || resultResponse
                                                                                                      .getCode() == 201 || resultResponse
                                                                                                                                   .getCode() == 202) {

                try {
                    return adapter.fromJson(body);
                } finally {
                    value.close();
                }
            } else {
                throw new ResultException(resultResponse.getCode(), resultResponse.getMsg().toString());
            }
        }
    }
}
