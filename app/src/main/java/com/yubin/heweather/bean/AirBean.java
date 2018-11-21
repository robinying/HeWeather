package com.yubin.heweather.bean;

import java.util.List;

/**
 * author : Yubin.Ying
 * time : 2018/11/20
 */
public class AirBean {
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
         * update : {"loc":"2018-11-20 15:45","utc":"2018-11-20 07:45"}
         * status : ok
         * air_now_city : {"aqi":"77","qlty":"良","main":"PM2.5","pm25":"56","pm10":"87","no2":"82","so2":"16","co":"1.1","o3":"45","pub_time":"2018-11-20 15:00"}
         * air_now_station : [{"air_sta":"区环保大楼","aqi":"87","asid":"CNA1234","co":"1.2","lat":"29.9108","lon":"121.836","main":"PM10","no2":"75","o3":"36","pm10":"123","pm25":"62","pub_time":"2018-11-20 15:00","qlty":"良","so2":"15"},{"air_sta":"万里学院","aqi":"87","asid":"CNA1235","co":"1.0","lat":"29.8208","lon":"121.56","main":"PM2.5","no2":"70","o3":"36","pm10":"97","pm25":"64","pub_time":"2018-11-20 15:00","qlty":"良","so2":"0"},{"air_sta":"龙赛医院","aqi":"75","asid":"CNA1236","co":"1.1","lat":"29.9572","lon":"121.713","main":"PM2.5","no2":"67","o3":"79","pm10":"79","pm25":"55","pub_time":"2018-11-20 15:00","qlty":"良","so2":"17"},{"air_sta":"三江中学","aqi":"112","asid":"CNA1237","co":"1.1","lat":"29.8906","lon":"121.554","main":"PM2.5","no2":"87","o3":"32","pm10":"119","pm25":"84","pub_time":"2018-11-20 15:00","qlty":"轻度污染","so2":"14"},{"air_sta":"钱湖水厂","aqi":"32","asid":"CNA1239","co":"0.8","lat":"29.7736","lon":"121.633","main":"-","no2":"0","o3":"83","pm10":"26","pm25":"22","pub_time":"2018-11-20 15:00","qlty":"优","so2":"0"},{"air_sta":"太古小学","aqi":"74","asid":"CNA1240","co":"1.1","lat":"29.8633","lon":"121.586","main":"PM2.5","no2":"84","o3":"26","pm10":"94","pm25":"54","pub_time":"2018-11-20 15:00","qlty":"良","so2":"16"},{"air_sta":"市环境监测中心","aqi":"70","asid":"CNA1241","co":"1.2","lat":"29.8506","lon":"121.524","main":"PM2.5","no2":"81","o3":"26","pm10":"75","pm25":"51","pub_time":"2018-11-20 15:00","qlty":"良","so2":"10"},{"air_sta":"万里国际学校","aqi":"69","asid":"CNA2871","co":"1.2","lat":"29.90166667","lon":"121.6147222","main":"PM2.5","no2":"110","o3":"39","pm10":"78","pm25":"50","pub_time":"2018-11-20 15:00","qlty":"良","so2":"19"}]
         */

        private BasicBean basic;
        private UpdateBean update;
        private String status;
        private AirNowCityBean air_now_city;
        private List<AirNowStationBean> air_now_station;

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

        public AirNowCityBean getAir_now_city() {
            return air_now_city;
        }

        public void setAir_now_city(AirNowCityBean air_now_city) {
            this.air_now_city = air_now_city;
        }

        public List<AirNowStationBean> getAir_now_station() {
            return air_now_station;
        }

        public void setAir_now_station(List<AirNowStationBean> air_now_station) {
            this.air_now_station = air_now_station;
        }
    }
}
