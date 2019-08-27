package com.shi.test.androidmvplearningrecord.module.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by long on 2016/8/23.
 * 基础 BaseView 接口
 */
public interface IBaseView {

    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 网络是否可用
     */
    boolean isNetworkAvailable();

    /**
     * 绑定生命周期
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLife();

}
