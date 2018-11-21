package com.yubin.heweather.bean;

import com.google.gson.annotations.SerializedName;

/**
 * author : Yubin.Ying
 * time : 2018/11/20
 */
public class AirNowStationBean {
    /**
     * air_sta : 区环保大楼
     * aqi : 87
     * asid : CNA1234
     * co : 1.2
     * lat : 29.9108
     * lon : 121.836
     * main : PM10
     * no2 : 75
     * o3 : 36
     * pm10 : 123
     * pm25 : 62
     * pub_time : 2018-11-20 15:00
     * qlty : 良
     * so2 : 15
     */

    @SerializedName("air_sta")
    private String air_sta;
    @SerializedName("aqi")
    private String aqi;
    @SerializedName("asid")
    private String asid;
    @SerializedName("co")
    private String co;
    @SerializedName("lat")
    private String lat;
    @SerializedName("lon")
    private String lon;
    @SerializedName("main")
    private String main;
    @SerializedName("no2")
    private String no2;
    @SerializedName("o3")
    private String o3;
    @SerializedName("pm10")
    private String pm10;
    @SerializedName("pm25")
    private String pm25;
    @SerializedName("pub_time")
    private String pub_time;
    @SerializedName("qlty")
    private String qlty;
    @SerializedName("so2")
    private String so2;

    public String getAir_sta() {
        return air_sta;
    }

    public void setAir_sta(String air_sta) {
        this.air_sta = air_sta;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getAsid() {
        return asid;
    }

    public void setAsid(String asid) {
        this.asid = asid;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getPub_time() {
        return pub_time;
    }

    public void setPub_time(String pub_time) {
        this.pub_time = pub_time;
    }

    public String getQlty() {
        return qlty;
    }

    public void setQlty(String qlty) {
        this.qlty = qlty;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }
}
