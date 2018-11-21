package com.yubin.heweather.ui.air;

import com.yubin.heweather.bean.AirBean;
import com.yubin.heweather.ui.mvp.BasePresenter;
import com.yubin.heweather.ui.mvp.BaseView;

/**
 * author : Yubin.Ying
 * time : 2018/11/20
 */
public interface AirContract {
    interface Presenter extends BasePresenter {
        void getAirData(String city);
    }

    interface View extends BaseView<Presenter> {
        void showAirData(AirBean.HeWeather6Bean airData);

        void showEmptyView(boolean show);

        void showLoading(boolean show);
    }
}
