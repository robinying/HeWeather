package com.yubin.heweather.bean;

import com.google.gson.annotations.SerializedName;

/**
 * author : Yubin.Ying
 * time : 2018/11/19
 */
public class BasicBean {
    /**
     * cid : CN101010100
     * location : 北京
     * parent_city : 北京
     * admin_area : 北京
     * cnty : 中国
     * lat : 39.90498734
     * lon : 116.4052887
     * tz : +8.00
     */

    @SerializedName("cid")
    private String cid;
    @SerializedName("location")
    private String location;
    @SerializedName("parent_city")
    private String parent_city;
    @SerializedName("admin_area")
    private String admin_area;
    @SerializedName("cnty")
    private String cnty;
    @SerializedName("lat")
    private String lat;
    @SerializedName("lon")
    private String lon;
    @SerializedName("tz")
    private String tz;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getParent_city() {
        return parent_city;
    }

    public void setParent_city(String parent_city) {
        this.parent_city = parent_city;
    }

    public String getAdmin_area() {
        return admin_area;
    }

    public void setAdmin_area(String admin_area) {
        this.admin_area = admin_area;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
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

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }
}
