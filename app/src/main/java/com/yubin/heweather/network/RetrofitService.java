package com.yubin.heweather.network;

import com.yubin.heweather.bean.AirBean;
import com.yubin.heweather.bean.NowWeatherBean;
import com.yubin.heweather.bean.WeatherBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * author : Yubin.Ying
 * time : 2018/11/19
 */
public interface RetrofitService {

    @GET("weather/now")
    Observable<NowWeatherBean> getNowWeather(@Query("location") String location, @Query("key") String key);


    @GET("weather")
    Observable<WeatherBean> getWeather(@Query("location") String location, @Query("key") String key);

    @GET("air")
    Observable<AirBean> getAirData(@Query("location") String city,@Query("key") String key);
}
