package com.shi.test.androidmvplearningrecord.module.activity;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.shi.test.androidmvplearningrecord.R;
import com.shi.test.androidmvplearningrecord.bean.HttpResult;
import com.shi.test.androidmvplearningrecord.bean.WelfarePhotoInfo;
import com.shi.test.androidmvplearningrecord.module.base.BaseActivity;
import com.shi.test.androidmvplearningrecord.module.model.TestModule;
import com.shi.test.androidmvplearningrecord.module.presenter.TestPresenter;
import com.shi.test.androidmvplearningrecord.module.view.TestView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;


/**
 * author 施镇杰
 * email 452239676@qq.com
 * created 2019/8/20 下午3:36
 */
public class MainActivity extends BaseActivity<TestPresenter> implements TestView{
    @BindView(R.id.tv)
    TextView tv;

    TestModule testModule;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        rxPermissionTest();

    }

    @Override
    protected void initData() {
        testModule=new TestModule(this);
        testModule.providePhotoSetPresenter();
    }

    private void rxPermissionTest() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA).subscribe(granted -> {
            if (granted) {
                // 打开相机
            } else {
                Toast.makeText(MainActivity.this, "App申请使用的权限被拒绝会影响部分功能的使用,下次打开记得同意当前权限!", Toast.LENGTH_LONG)
                     .show();
            }
        });
    }

    @Override
    public void loadData(HttpResult<List<WelfarePhotoInfo>> listHttpResult) {
        String tv1 = "";
        for (int i = 0; i < listHttpResult.getResults().size(); i++) {
            tv1 = tv1 + listHttpResult.getResults().get(i).getUrl() + "\n";
        }
        tv.setText(tv1);
    }
}
