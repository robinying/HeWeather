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
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yubin.heweather.R;
import com.yubin.heweather.bean.AirBean;
import com.yubin.heweather.config.Constant;
import com.yubin.heweather.model.HeWeatherModel;
import com.yubin.heweather.ui.air.AirContract;
import com.yubin.heweather.ui.air.AirPresenter;
import com.yubin.heweather.ui.base.BaseFragment;
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
public class AirFragment extends BaseFragment implements AirContract.View {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.air_refresh)
    SmartRefreshLayout airRefresh;
    private AirContract.Presenter mPresenter;
    private AirAdapter airAdapter;

    @Override
    public void onResume() {
        super.onResume();
        String city = Basepreference.getString(Constant.USE_CITY, "北京");
        mPresenter.getAirData(city);
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
        return R.layout.fragment_air;
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        airRefresh.setRefreshHeader(new BezierCircleHeader(getContext()));
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        new AirPresenter(this , HeWeatherModel.getInstance());

    }

    @Override
    protected void initData() {
        super.initData();
        RxBus.getDefault()
                .toObservable(ChangeCityEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(event -> isVisible())
                .doOnNext(event -> {
                    if (mPresenter != null) {
                        String city  = Basepreference.getString(Constant.USE_CITY);
                        mPresenter.getAirData(city);
                    }
                })
                .subscribe();
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        airRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                if(mPresenter !=null){
                    String city  = Basepreference.getString(Constant.USE_CITY);
                    mPresenter.getAirData(city);
                }
                airRefresh.finishRefresh(1000);
            }
        });
    }


    @Override
    public void showAirData(AirBean.HeWeather6Bean airData) {
        airAdapter = new AirAdapter(airData);
        recyclerview.setAdapter(airAdapter);
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
    public void setPresenter(AirContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isResumed();
    }
}
