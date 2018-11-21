package com.yubin.heweather.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * author : Yubin.Ying
 * time : 2018/11/19
 */
public class WeatherBean {
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
         * basic : {"cid":"CN101210401","location":"宁波","parent_city":"宁波","admin_area":"浙江","cnty":"中国","lat":"29.86838722","lon":"121.54978943","tz":"+8.00"}
         * update : {"loc":"2018-11-19 12:45","utc":"2018-11-19 04:45"}
         * status : ok
         * now : {"cloud":"75","cond_code":"101","cond_txt":"多云","fl":"12","hum":"78","pcpn":"0.1","pres":"1025","tmp":"13","vis":"7","wind_deg":"294","wind_dir":"西北风","wind_sc":"1","wind_spd":"5"}
         * daily_forecast : [{"cond_code_d":"104","cond_code_n":"104","cond_txt_d":"阴","cond_txt_n":"阴","date":"2018-11-19","hum":"85","mr":"14:39","ms":"02:04","pcpn":"0.0","pop":"8","pres":"1024","sr":"06:22","ss":"16:55","tmp_max":"15","tmp_min":"10","uv_index":"1","vis":"16","wind_deg":"355","wind_dir":"北风","wind_sc":"3-4","wind_spd":"24"},{"cond_code_d":"305","cond_code_n":"305","cond_txt_d":"小雨","cond_txt_n":"小雨","date":"2018-11-20","hum":"79","mr":"15:14","ms":"03:00","pcpn":"1.0","pop":"55","pres":"1025","sr":"06:23","ss":"16:55","tmp_max":"16","tmp_min":"13","uv_index":"1","vis":"19","wind_deg":"145","wind_dir":"东南风","wind_sc":"3-4","wind_spd":"23"},{"cond_code_d":"305","cond_code_n":"305","cond_txt_d":"小雨","cond_txt_n":"小雨","date":"2018-11-21","hum":"91","mr":"15:50","ms":"03:58","pcpn":"0.0","pop":"0","pres":"1020","sr":"06:24","ss":"16:55","tmp_max":"15","tmp_min":"11","uv_index":"0","vis":"12","wind_deg":"297","wind_dir":"西北风","wind_sc":"3-4","wind_spd":"22"}]
         * lifestyle : [{"type":"comf","brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},{"type":"drsg","brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},{"type":"flu","brf":"较易发","txt":"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。"},{"type":"sport","brf":"较适宜","txt":"阴天，较适宜进行各种户内外运动。"},{"type":"trav","brf":"适宜","txt":"天气较好，风稍大，但温度适宜，总体来说还是好天气。这样的天气适宜旅游，您可以尽情享受大自然的风光。"},{"type":"uv","brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"},{"type":"cw","brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},{"type":"air","brf":"良","txt":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"}]
         */

        @SerializedName("basic")
        private BasicBean basic;
        @SerializedName("update")
        private UpdateBean update;
        @SerializedName("status")
        private String status;
        @SerializedName("now")
        private NowBean now;
        @SerializedName("daily_forecast")
        private List<DailyForecastBean> daily_forecast;
        @SerializedName("lifestyle")
        private List<LifestyleBean> lifestyle;
        @SerializedName("hourly")
        private List<HourlyBean> hourly;

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

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public List<LifestyleBean> getLifestyle() {
            return lifestyle;
        }

        public void setLifestyle(List<LifestyleBean> lifestyle) {
            this.lifestyle = lifestyle;
        }

        public List<HourlyBean> getHourly() {
            return hourly;
        }

        public void setHourly(List<HourlyBean> hourly) {
            this.hourly = hourly;
        }

    }
}
