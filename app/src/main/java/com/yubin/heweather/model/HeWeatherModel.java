package com.yubin.heweather.model;

import com.blankj.utilcode.util.ToastUtils;
import com.yubin.heweather.bean.AirBean;
import com.yubin.heweather.bean.WeatherBean;
import com.yubin.heweather.config.Constant;
import com.yubin.heweather.network.RetrofitClient;
import com.yubin.heweather.network.RetrofitService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * author : Yubin.Ying
 * time : 2018/11/19
 */
public class HeWeatherModel {
    private static HeWeatherModel INSTANCE;
    private HeWeatherModel() {

    }

    public static HeWeatherModel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HeWeatherModel();
        }
        return INSTANCE;
    }

    public Observable<List<WeatherBean.HeWeather6Bean>> getWeatherData(String city){
        return RetrofitClient.getInstance().create(RetrofitService.class)
                .getWeather(city, Constant.HEWEATHER_KEY)
                .filter(new Predicate<WeatherBean>() {
                    @Override
                    public boolean test(WeatherBean weatherBean) throws Exception {
                        if(!"ok".equals(weatherBean.getHeWeather6().get(0).getStatus())){
                            ToastUtils.showShort(weatherBean.getHeWeather6().get(0).getStatus());
                        }
                        return "ok".equals(weatherBean.getHeWeather6().get(0).getStatus());
                    }
        }).flatMap(new Function<WeatherBean, ObservableSource<List<WeatherBean.HeWeather6Bean>>>() {
                    @Override
                    public ObservableSource<List<WeatherBean.HeWeather6Bean>> apply(WeatherBean weatherBean) throws Exception {
                        return Observable.just(weatherBean.getHeWeather6());
                    }
                });
    }

    public Observable<List<AirBean.HeWeather6Bean>> getAirData(String city){
        return RetrofitClient.getInstance().create(RetrofitService.class)
                .getAirData(city,Constant.HEWEATHER_KEY)
                .filter(new Predicate<AirBean>() {
                    @Override
                    public boolean test(AirBean airBean) throws Exception {
                        if(!"ok".equals(airBean.getHeWeather6().get(0).getStatus())){
                            ToastUtils.showShort(airBean.getHeWeather6().get(0).getStatus());
                        }
                        return "ok".equals(airBean.getHeWeather6().get(0).getStatus());
                    }
                }).flatMap(new Function<AirBean, ObservableSource<List<AirBean.HeWeather6Bean>>>() {
                    @Override
                    public ObservableSource<List<AirBean.HeWeather6Bean>> apply(AirBean airBean) throws Exception {
                        return Observable.just(airBean.getHeWeather6());
                    }
                });
    }
}
