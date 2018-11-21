package com.yubin.heweather.ui.main;

import com.yubin.heweather.bean.WeatherBean;
import com.yubin.heweather.ui.mvp.BasePresenter;
import com.yubin.heweather.ui.mvp.BaseView;

/**
 * author : Yubin.Ying
 * time : 2018/11/19
 */
public interface MainContract{
    interface Presenter extends BasePresenter{
        void getWeatherData(String city);
    }

    interface View extends BaseView<Presenter> {
        void showWeatherData(WeatherBean.HeWeather6Bean heWeather6Bean);

        void showEmptyView(boolean show);

        void showLoading(boolean show);
    }
}
