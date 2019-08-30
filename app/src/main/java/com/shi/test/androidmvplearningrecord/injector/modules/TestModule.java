package com.shi.test.androidmvplearningrecord.injector.modules;

import com.shi.test.androidmvplearningrecord.module.activity.TestActivity;
import com.shi.test.androidmvplearningrecord.module.presenter.TestPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by 施镇杰 on 2019/8/27
 */
@Module
public class TestModule {
    private final TestActivity mView;
    private TestPresenter testPresenter;

    public TestModule(TestActivity view) {
        mView = view;
        testPresenter = new TestPresenter(mView);
    }



    @Provides
    public TestPresenter providePhotoSetPresenter() {
        return testPresenter;
    }
}
