package com.yubin.heweather.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * author : Yubin.Ying
 * time : 2018/11/19
 */
public class NowWeatherBean {

    @SerializedName("HeWeather6")
    private List<HeWeather6Bean> HeWeather6;

    public List<HeWeather6Bean> getHeWeather6() {
        return HeWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }

    public static class HeWeather6Bean {
        /**
         * basic : {"cid":"CN101010100","location":"北京","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.90498734","lon":"116.4052887","tz":"+8.00"}
         * update : {"loc":"2018-11-19 09:45","utc":"2018-11-19 01:45"}
         * status : ok
         * now : {"cloud":"0","cond_code":"100","cond_txt":"晴","fl":"2","hum":"33","pcpn":"0.0","pres":"1024","tmp":"5","vis":"14","wind_deg":"85","wind_dir":"东风","wind_sc":"2","wind_spd":"7"}
         */

        @SerializedName("basic")
        private BasicBean basic;
        @SerializedName("update")
        private UpdateBean update;
        @SerializedName("status")
        private String status;
        @SerializedName("now")
        private NowBean now;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

    }
}
