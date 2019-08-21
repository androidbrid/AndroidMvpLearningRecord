package com.shi.test.androidmvplearningrecord;

import android.app.Application;
import android.content.Context;

/**
 * Created by 施镇杰 on 2019/8/21
 */
public class MyApplication extends Application{
    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }
    public static Context getContext() {
        return sContext;
    }
}
