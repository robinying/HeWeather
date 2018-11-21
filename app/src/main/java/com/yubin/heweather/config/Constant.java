package com.yubin.heweather.config;


import android.Manifest;

import com.yubin.heweather.App;

import java.io.File;

/**
 * author : Yubin.Ying
 * time : 2018/11/19
 */
public class Constant {
    public static final String LOCATION_CITY = "location_city";
    public static final String LOCATION_DISTRICT = "location_district";
    public static final String USE_CITY = "use_city";
    public static final String USE_DISTRICT = "use_district";
    public static final String HEWEATHER_KEY = "e4f463c603ec41628d4d497b5eccbe6a";

    public static final String[] REQUEST_PERMISSIONS = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE};

    public static final String NET_CACHE = App.getAppInstance().getCacheDir() + File.separator + "NetCache";
}
