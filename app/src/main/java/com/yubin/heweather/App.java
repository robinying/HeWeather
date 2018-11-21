package com.yubin.heweather;

import android.app.Application;

import com.yubin.heweather.utils.XLog;

/**
 * author : Yubin.Ying
 * time : 2018/11/19
 */
public class App extends Application {
    private static App appInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        if (!BuildConfig.DEBUG) {
            XLog.closeLog();
        }
    }

    public static App getAppInstance(){
        return appInstance;
    }
}
