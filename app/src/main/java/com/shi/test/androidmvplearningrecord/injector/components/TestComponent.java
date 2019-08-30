package com.shi.test.androidmvplearningrecord.injector.components;

import com.shi.test.androidmvplearningrecord.module.activity.TestActivity;
import com.shi.test.androidmvplearningrecord.injector.modules.TestModule;
import dagger.Component;

/**
 * Created by 施镇杰 on 2019/8/28
 */
@Component(modules = {TestModule.class})
public interface TestComponent {
    //第三步  写一个方法 绑定Activity /Fragment
    void inject(TestActivity activity);
}
