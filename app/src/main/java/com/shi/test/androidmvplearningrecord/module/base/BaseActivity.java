package com.shi.test.androidmvplearningrecord.module.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.gyf.immersionbar.ImmersionBar;
import com.shi.test.androidmvplearningrecord.R;
import com.shi.test.androidmvplearningrecord.utils.NetUtil;
import com.shi.test.androidmvplearningrecord.utils.customview.CustomDialog;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wang.avi.AVLoadingIndicatorView;
import es.dmoral.toasty.Toasty;

import javax.inject.Inject;

/**
 * Activity 基类
 * author 施镇杰
 * email 452239676@qq.com
 * created 2019/8/27 下午5:45
 */
public abstract class BaseActivity<T extends IBasePresenter> extends RxAppCompatActivity implements IBaseView {

    @Inject
    protected T mPresenter;
    public CustomDialog customDialog;
    AVLoadingIndicatorView avi;

    private TextView tvTitle, tv_right;
    private LinearLayout viewContent;
    public Toolbar toolbar;
    public ImageView mIv, base_img_back;
    BaseActivity activity;

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews(Bundle savedInstanceState);

    /**
     * Dagger 注入
     */
    protected abstract void initInjector();

    /**
     * 加载数据
     */
    protected abstract void initData();

    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
        //设置共同沉浸式样式
        toolbar.setBackgroundColor(getResources().getColor(R.color.deeppink));
        ImmersionBar.with(this).titleBar(toolbar).navigationBarColor(R.color.deeppink).init();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_toolbar_layout);

        //1、设置支出，并不显示项目的title文字
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mIv = (ImageView) findViewById(R.id.mIv);
        base_img_back = (ImageView) findViewById(R.id.base_img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tv_right = (TextView) findViewById(R.id.base_tv_right);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        activity = this;
        //2、将子类的布局解析到 FrameLayout 里面
        viewContent = (LinearLayout) findViewById(R.id.viewContent);
        LayoutInflater.from(this).inflate(getLayoutId(), viewContent);
        ButterKnife.bind(this);
        initInjector();
        initImmersionBar();
        initData();
        initViews(savedInstanceState);
    }

    @Override
    public void showLoading() {
        if (customDialog == null) {
            customDialog = new CustomDialog(this, R.style.Translucent_NoTitle);
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_loading, null);
            avi = (AVLoadingIndicatorView) view.findViewById(R.id.indicator);
            customDialog.setContentView(view);
            customDialog.setOnKeyListener((dialog, keyCode, event) -> {
                customDialog.dismiss();
                return false;
            });
            customDialog.setOnDismissListener(dialog -> {
                if (mPresenter != null) {
                    bindToLife();
                }
            });
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                customDialog.create();
            }
            avi.show();
        }
        customDialog.setCancelable(false);
        if (!isFinishing() && !customDialog.isShowing()) {
            customDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (customDialog != null && customDialog.isShowing()) {
            avi.hide();
            customDialog.dismiss();
        }
    }

    /**
     * 成功自带Icon提示
     */
    public void showSuccessIconToast(Context context, String string) {
        Toasty.success(context, string, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 失败自带Icon提示
     */
    public void showErrorIconToast(Context context, String string) {
        Toasty.error(context, string, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 自带Icon提示
     */
    public void showInfoIconToast(Context context, String string) {
        Toasty.info(context, string, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 警告自带Icon提示
     */
    public void showWarningIconToast(Context context, String string) {
        Toasty.warning(context, string, Toast.LENGTH_SHORT, true).show();
    }


    /**
     * 普通自带Icon提示
     */
    public void showNormalIconToast(Context context, String string) {
        Toasty.normal(context, string).show();
    }

    /**
     * 普通自定义提示
     */
    public void showNormalToast(Context context, String string, Drawable drawable) {
        Toasty.normal(context, string, drawable).show();
    }

    /**
     * 自定义带Icon提示
     */
    public void showCustomIconToast(Context context, String string, Drawable drawable, int tintColor, int textColor,
                                    int time, boolean withIcon, boolean shouldTint) {
        Toasty.custom(context, string, drawable, tintColor, textColor, time, withIcon, shouldTint).show();
    }

    /**
     * 自定义不带Icon提示
     */
    public void showCustomToast(Context context, String string, Drawable drawable, int tintColor, int textColor,
                                int time, boolean withIcon, boolean shouldTint) {
        Toasty.custom(context, string, drawable, tintColor, textColor, time, withIcon, shouldTint).show();
    }

    @Override
    public boolean isNetworkAvailable() {
        return NetUtil.isNetworkAvailable(this);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    /**
     * 设置页面标题
     *
     * @param title 标题文字
     */
    protected void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
    }

    /**
     * 设置页面右标题
     *
     * @param title 右标题文字
     */
    protected void setRightTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            tv_right.setText(title);
        }
    }

    /**
     * 设置图片状态栏，同理也可以设置banner状态栏，暂时没写
     *
     * @param imgRe 图片资源
     */
    protected void setImage(int imgRe) {
        toolbar.setVisibility(View.GONE);
        base_img_back.setVisibility(View.GONE);
        tv_right.setVisibility(View.GONE);
        mIv.setVisibility(View.VISIBLE);
        Glide.with(this).asBitmap().load(imgRe)
             .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
             .into(mIv);
    }

    //右侧第一个文字点击事件
    OnRightTvListener onRightTvListener;

    public void setOnRightTvListener(OnRightTvListener onRightTvListener) {
        this.onRightTvListener = onRightTvListener;
    }

    public interface OnRightTvListener {
        void onRightTvListener();
    }

    @OnClick({R.id.base_img_back, R.id.base_tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.base_img_back:
                finish();
                break;
            case R.id.base_tv_right:
                if (onRightTvListener != null) {
                    onRightTvListener.onRightTvListener();
                }
                break;
            default:
                break;
        }
    }
}
