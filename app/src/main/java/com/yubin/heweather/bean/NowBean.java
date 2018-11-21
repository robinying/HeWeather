package com.yubin.heweather.bean;

import com.google.gson.annotations.SerializedName;

/**
 * author : Yubin.Ying
 * time : 2018/11/19
 */
public class NowBean {
    /**
     * cloud : 0
     * cond_code : 100
     * cond_txt : 晴
     * fl : 2
     * hum : 33
     * pcpn : 0.0
     * pres : 1024
     * tmp : 5
     * vis : 14
     * wind_deg : 85
     * wind_dir : 东风
     * wind_sc : 2
     * wind_spd : 7
     */

    @SerializedName("cloud")
    private String cloud;
    @SerializedName("cond_code")
    private String cond_code;
    @SerializedName("cond_txt")
    private String cond_txt;
    @SerializedName("fl")
    private String fl;
    @SerializedName("hum")
    private String hum;
    @SerializedName("pcpn")
    private String pcpn;
    @SerializedName("pres")
    private String pres;
    @SerializedName("tmp")
    private String tmp;
    @SerializedName("vis")
    private String vis;
    @SerializedName("wind_deg")
    private String wind_deg;
    @SerializedName("wind_dir")
    private String wind_dir;
    @SerializedName("wind_sc")
    private String wind_sc;
    @SerializedName("wind_spd")
    private String wind_spd;

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getCond_code() {
        return cond_code;
    }

    public void setCond_code(String cond_code) {
        this.cond_code = cond_code;
    }

    public String getCond_txt() {
        return cond_txt;
    }

    public void setCond_txt(String cond_txt) {
        this.cond_txt = cond_txt;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public String getWind_deg() {
        return wind_deg;
    }

    public void setWind_deg(String wind_deg) {
        this.wind_deg = wind_deg;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public String getWind_sc() {
        return wind_sc;
    }

    public void setWind_sc(String wind_sc) {
        this.wind_sc = wind_sc;
    }

    public String getWind_spd() {
        return wind_spd;
    }

    public void setWind_spd(String wind_spd) {
        this.wind_spd = wind_spd;
    }
}
