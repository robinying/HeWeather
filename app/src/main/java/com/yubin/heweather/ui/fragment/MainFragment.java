package com.yubin.heweather.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yubin.heweather.R;
import com.yubin.heweather.bean.WeatherBean;
import com.yubin.heweather.config.Constant;
import com.yubin.heweather.model.HeWeatherModel;
import com.yubin.heweather.ui.base.BaseFragment;
import com.yubin.heweather.ui.main.MainContract;
import com.yubin.heweather.ui.main.MainPresenter;
import com.yubin.heweather.utils.Basepreference;
import com.yubin.heweather.utils.ChangeCityEvent;
import com.yubin.heweather.utils.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * author : Yubin.Ying
 * time : 2018/11/20
 */
public class MainFragment extends BaseFragment implements MainContract.View {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.main_refresh)
    SmartRefreshLayout mainRefresh;
    private MainContract.Presenter mPresenter;
    private WeatherAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();
        String district = Basepreference.getString(Constant.LOCATION_DISTRICT);
        mPresenter.getWeatherData(district);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        new MainPresenter(this, HeWeatherModel.getInstance());
        mainRefresh.setRefreshHeader(new BezierCircleHeader(getContext()));
        RxBus.getDefault()
                .toObservable(ChangeCityEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(event -> isVisible())
                .doOnNext(event -> {
                    if (mPresenter != null) {
                        String city = Basepreference.getString(Constant.LOCATION_CITY);
                        String district = Basepreference.getString(Constant.LOCATION_DISTRICT);
                        mPresenter.getWeatherData(district);
                    }
                })
                .subscribe();
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        mainRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                String district = Basepreference.getString(Constant.LOCATION_DISTRICT);
                mPresenter.getWeatherData(district);
                mainRefresh.finishRefresh(1000);
            }
        });
    }

    @Override
    public void showWeatherData(WeatherBean.HeWeather6Bean heWeather6Bean) {
        adapter = new WeatherAdapter(heWeather6Bean);
        recyclerview.setAdapter(adapter);

    }

    @Override
    public void showEmptyView(boolean show) {

    }

    @Override
    public void showLoading(boolean show) {
        if (show) {
            showWaitDialog();
        } else {
            hideWaitDialog();
        }
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isResumed();
    }

}
