package com.yubin.heweather.bean;

import com.google.gson.annotations.SerializedName;

/**
 * author : Yubin.Ying
 * time : 2018/11/19
 */
public  class UpdateBean {
    /**
     * loc : 2018-11-19 09:45
     * utc : 2018-11-19 01:45
     */

    @SerializedName("loc")
    private String loc;
    @SerializedName("utc")
    private String utc;

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }
}

