package com.shi.test.androidmvplearningrecord.module.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.shi.test.androidmvplearningrecord.R;
import com.shi.test.androidmvplearningrecord.bean.HttpResult;
import com.shi.test.androidmvplearningrecord.bean.WelfarePhotoInfo;
import com.shi.test.androidmvplearningrecord.bean.WelfarePhotoList;
import com.shi.test.androidmvplearningrecord.http.HttpMethods;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.List;


/**
  *
  *
  *author 施镇杰
  *email 452239676@qq.com
  *created 2019/8/20 下午3:36
  */
public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rxPermissionTest();

        HttpMethods.getInstance().getWelfareHttpApi().getWelfarePhoto(10)
                   .subscribeOn(Schedulers.io())
                   .unsubscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new Observer<HttpResult<List<WelfarePhotoInfo>>>() {
                       @Override
                       public void onError(Throwable e) {
                           Log.d("111111", "onError: "+e.getMessage());
                       }

                       @Override
                       public void onComplete() {

                       }

                       @Override
                       public void onSubscribe(Disposable d) {

                       }

                       @Override
                       public void onNext(HttpResult<List<WelfarePhotoInfo>> listHttpResult) {
//                           Log.d("111111", "onNext: "+listHttpResult.getParams().size());
                       }

                   });
    }
    private void rxPermissionTest() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean granted) throws Exception {
                if (granted) {
                    // 打开相机
                } else {
                    Toast.makeText(MainActivity.this,"App申请使用的权限被拒绝会影响部分功能的使用,下次打开记得同意当前权限!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
