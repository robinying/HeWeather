package com.yubin.heweather.ui.mvp;

import android.view.View;

/**
 * author : Yubin.Ying
 * time : 2018/11/19
 */
public interface BaseView<T> {
    void setPresenter(T presenter);
    boolean isActive();
}
