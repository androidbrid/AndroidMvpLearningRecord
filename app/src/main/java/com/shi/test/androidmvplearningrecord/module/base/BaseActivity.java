package com.shi.test.androidmvplearningrecord.module.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import butterknife.ButterKnife;
import com.shi.test.androidmvplearningrecord.utils.NetUtil;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
  *
  *Activity 基类
  *author 施镇杰
  *email 452239676@qq.com
  *created 2019/8/27 下午5:45
  */
public abstract class BaseActivity<T extends IBasePresenter> extends RxAppCompatActivity implements IBaseView{

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 加载数据
     */
    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);
        initViews();
        initData();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public boolean isNetworkAvailable() {
        return NetUtil.isNetworkAvailable(this);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }
}
