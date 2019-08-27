package com.shi.test.androidmvplearningrecord.module.model;

import com.shi.test.androidmvplearningrecord.module.activity.MainActivity;
import com.shi.test.androidmvplearningrecord.module.base.IBasePresenter;
import com.shi.test.androidmvplearningrecord.module.presenter.TestPresenter;

/**
 * Created by 施镇杰 on 2019/8/27
 */
public class TestModule {
    private final MainActivity mView;

    public TestModule(MainActivity view) {
        mView = view;
    }

    public IBasePresenter providePhotoSetPresenter() {
        return new TestPresenter(mView).getData();
    }
}
