package com.yubin.heweather.ui.air;

import com.yubin.heweather.bean.AirBean;
import com.yubin.heweather.model.HeWeatherModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * author : Yubin.Ying
 * time : 2018/11/20
 */
public class AirPresenter implements AirContract.Presenter {
    private AirContract.View mView;
    private HeWeatherModel heWeatherModel;

    public AirPresenter(AirContract.View view , HeWeatherModel heWeatherModel){
        this.mView = view;
        mView.setPresenter(this);
        this.heWeatherModel = heWeatherModel;

    }
    @Override
    public void getAirData(String city) {
        heWeatherModel.getAirData(city)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoading(true);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<AirBean.HeWeather6Bean>>() {
                    @Override
                    public void onNext(List<AirBean.HeWeather6Bean> airData) {
                        mView.showAirData(airData.get(0));
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
