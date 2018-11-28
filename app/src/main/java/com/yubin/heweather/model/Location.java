package com.yubin.heweather.model;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationClientOption.AMapLocationProtocol;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.ToastUtils;
import com.yubin.heweather.App;
import com.yubin.heweather.R;
import com.yubin.heweather.config.Constant;
import com.yubin.heweather.utils.Basepreference;
import com.yubin.heweather.utils.ChangeCityEvent;
import com.yubin.heweather.utils.RxBus;
import com.yubin.heweather.utils.XLog;

/**
 * author : Yubin.Ying
 * time : 2018/11/19
 */
public class Location {
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption mOption = new AMapLocationClientOption();

    public Location(){
        initLocation();
    }
    private void initLocation(){
        //初始化client
        locationClient = new AMapLocationClient(App.getAppInstance());
        //设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    private AMapLocationClientOption getDefaultOption(){
        mOption.setLocationMode(AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (null != loc && loc.getErrorCode() == 0) {
                ToastUtils.showShort("当前定位:" + loc.getCity() + loc.getDistrict());
                /**
                 * 当位置变化时更新user的位置，获取到当前的定位
                 */
                boolean changeLocation = Basepreference.contains(Constant.LOCATION_DISTRICT)
                        && Basepreference.contains(Constant.USE_DISTRICT)
                        && !loc.getDistrict().equals(Basepreference.getString(Constant.LOCATION_DISTRICT))
                        && Basepreference.getString(Constant.USE_DISTRICT).equals(Basepreference.getString(Constant.LOCATION_DISTRICT));
                Basepreference.putString(Constant.LOCATION_CITY, loc.getCity());
                Basepreference.putString(Constant.LOCATION_DISTRICT, loc.getDistrict());
                if (!Basepreference.contains(Constant.USE_DISTRICT) && !Basepreference.contains(Constant.USE_CITY) || changeLocation) {
                    Basepreference.putString(Constant.USE_CITY, loc.getCity());
                    Basepreference.putString(Constant.USE_DISTRICT, loc.getDistrict());
                }
                RxBus.getDefault().post(new ChangeCityEvent());
            } else {
                ToastUtils.showShort(R.string.location_failed);
            }
        }
    };

    /**
     * 开始定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    public void startLocation(){
        // 设置定位参数
        locationClient.setLocationOption(mOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    public void stopLocation(){
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    public void destroyLocation(){
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
        }
    }
}
