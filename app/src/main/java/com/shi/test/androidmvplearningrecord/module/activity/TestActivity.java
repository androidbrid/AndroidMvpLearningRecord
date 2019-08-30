package com.shi.test.androidmvplearningrecord.module.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.gyf.immersionbar.ImmersionBar;
import com.shi.test.androidmvplearningrecord.R;
import com.shi.test.androidmvplearningrecord.bean.HttpResult;
import com.shi.test.androidmvplearningrecord.bean.WelfarePhotoInfo;
import com.shi.test.androidmvplearningrecord.injector.components.DaggerTestComponent;
import com.shi.test.androidmvplearningrecord.injector.modules.TestModule;
import com.shi.test.androidmvplearningrecord.module.base.BaseActivity;
import com.shi.test.androidmvplearningrecord.module.presenter.TestPresenter;
import com.shi.test.androidmvplearningrecord.module.view.TestView;
import java.util.List;

public class TestActivity extends BaseActivity<TestPresenter> implements TestView {

    @BindView(R.id.tv)
    TextView tv;

    OnRightTvListener onRightTvListener= () -> showSuccessIconToast(TestActivity.this,"测试");
    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        //图片状态栏设置
        ImmersionBar.with(this).statusBarView(toolbar)
                    .navigationBarColor(R.color.colorPrimary)
                    .fullScreen(true)
                    .addTag("PicAndColor")
                    .init();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        //setTitle("测试");
        //setRightTitle("测试");
        //setOnRightTvListener(onRightTvListener);
        setImage(R.drawable.icon_img);
    }

    @Override
    protected void initInjector() {
        DaggerTestComponent.builder()
                           .testModule(new TestModule(this))
                           .build()
                           .inject(this);
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    public void loadData(HttpResult<List<WelfarePhotoInfo>> listHttpResult) {
        String tv1 = "";
        for (int i = 0; i < listHttpResult.getResults().size(); i++) {
            tv1 = tv1 + listHttpResult.getResults().get(i).getUrl() + "\n";
        }
        tv.setText(tv1);
    }

    @Override
    public void showError(String s) {
        showErrorIconToast(this, s);
    }
}
