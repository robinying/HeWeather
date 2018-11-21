package com.yubin.heweather.ui.main;

import com.yubin.heweather.bean.WeatherBean;
import com.yubin.heweather.model.HeWeatherModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * author : Yubin.Ying
 * time : 2018/11/19
 */
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private HeWeatherModel heWeatherModel;

    public MainPresenter(MainContract.View view, HeWeatherModel heWeatherModel) {
        this.mView = view;
        mView.setPresenter(this);
        this.heWeatherModel = heWeatherModel;

    }
    @Override
    public void getWeatherData(String city) {
        heWeatherModel.getWeatherData(city)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoading(true);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<WeatherBean.HeWeather6Bean>>() {
                    @Override
                    public void onNext(List<WeatherBean.HeWeather6Bean> heWeather6Beans) {

                        mView.showWeatherData(heWeather6Beans.get(0));
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showEmptyView(true);
                    }

                    @Override
                    public void onComplete() {
                        mView.showLoading(false);
                    }
                });
    }
}
