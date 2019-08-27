package com.shi.test.androidmvplearningrecord.module.presenter;

import com.shi.test.androidmvplearningrecord.bean.HttpResult;
import com.shi.test.androidmvplearningrecord.bean.WelfarePhotoInfo;
import com.shi.test.androidmvplearningrecord.http.HttpMethods;
import com.shi.test.androidmvplearningrecord.module.base.BaseSubScriber;
import com.shi.test.androidmvplearningrecord.module.base.IBasePresenter;
import com.shi.test.androidmvplearningrecord.module.base.OnBaseListener;
import com.shi.test.androidmvplearningrecord.module.view.TestView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

/**
 * Created by 施镇杰 on 2019/8/27
 */
public class TestPresenter implements IBasePresenter {

    private final TestView mView;

    public TestPresenter(TestView view) {
        mView = view;
    }


    @Override
    public IBasePresenter getData() {
        if(mView.isNetworkAvailable()){
            HttpMethods.getInstance(false).getWelfareHttpApi().getWelfarePhoto(10)
                       .subscribeOn(Schedulers.io())
                       .unsubscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .compose(this.mView.<HttpResult<List<WelfarePhotoInfo>>>bindToLife())
                       .subscribe(new BaseSubScriber(new OnBaseListener<HttpResult<List<WelfarePhotoInfo>>>() {
                           @Override
                           public void onSuccess(HttpResult<List<WelfarePhotoInfo>> result) {
                               mView.loadData(result);
                               mView.hideLoading();
                           }

                           @Override
                           public void onError(int code, String error) {
                               //Toast.makeText(MainActivity.this, "code:" + code + "error:" + error,Toast.LENGTH_LONG).show();
                               //Log.d("111111", "onError: " + error);
                           }

                           @Override
                           public void onCompleted() {
                               mView.hideLoading();
                           }
                       }));
        }else{

        }

        return null;
    }
}
