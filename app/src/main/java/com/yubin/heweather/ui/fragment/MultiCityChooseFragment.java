package com.yubin.heweather.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yubin.heweather.App;
import com.yubin.heweather.R;
import com.yubin.heweather.bean.CityBean;
import com.yubin.heweather.bean.CityBeanDao;
import com.yubin.heweather.config.Constant;
import com.yubin.heweather.ui.base.BaseFragment;
import com.yubin.heweather.utils.Basepreference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author : Yubin.Ying
 * time : 2018/11/20
 */
public class MultiCityChooseFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.city_refresh)
    SmartRefreshLayout cityRefresh;
    @BindView(R.id.empty_view)
    LinearLayout emptyView;
    private CityBeanDao cityBeanDao;
    private List<CityBean> cityData;
    private CityAdapter cityAdapter;

    @Override
    public void onResume() {
        super.onResume();
        if(cityData !=null){
            cityAdapter = new CityAdapter(cityData);
            recyclerview.setAdapter(cityAdapter);
        }
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
        return R.layout.fragment_multi_city;
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        initCityData();
        cityRefresh.setRefreshHeader(new BezierCircleHeader(getContext()));
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        cityRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                initCityData();
                cityAdapter.notifyDataSetChanged();
                cityRefresh.finishRefresh(1000);

            }
        });
    }


    private void initCityData() {
        cityBeanDao = App.getAppInstance().getDaoSession().getCityBeanDao();
        if (cityBeanDao.loadAll().size() > 0) {
            cityData = cityBeanDao.loadAll();
        } else {
            cityData = new ArrayList<>();
        }
        boolean addLocation = true;
        for (CityBean cityBean : cityBeanDao.loadAll()) {
            if (Basepreference.getString(Constant.LOCATION_DISTRICT).equals(cityBean.getDiscrict())
                    && Basepreference.getString(Constant.LOCATION_CITY).equals(cityBean.getCity())) {
                addLocation = false;
                break;
            }
        }
        if (addLocation) {
            CityBean locationcity = new CityBean();
            locationcity.setDiscrict(Basepreference.getString(Constant.LOCATION_DISTRICT));
            locationcity.setCity(Basepreference.getString(Constant.LOCATION_CITY));
            cityData.add(locationcity);
        }
    }
}
