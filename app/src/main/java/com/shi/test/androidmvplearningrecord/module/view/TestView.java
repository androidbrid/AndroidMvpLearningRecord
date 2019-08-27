package com.shi.test.androidmvplearningrecord.module.view;

import com.shi.test.androidmvplearningrecord.bean.HttpResult;
import com.shi.test.androidmvplearningrecord.bean.WelfarePhotoInfo;
import com.shi.test.androidmvplearningrecord.module.base.IBaseView;

import java.util.List;

/**
 * Created by 施镇杰 on 2019/8/27
 */
public interface TestView extends IBaseView{

    void loadData(HttpResult<List<WelfarePhotoInfo>> listHttpResult);
}
