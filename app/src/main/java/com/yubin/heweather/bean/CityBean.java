package com.yubin.heweather.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author : Yubin.Ying
 * time : 2018/11/21
 */
@Entity
public class CityBean {

    @Id(autoincrement = true)
    private Long id;

    private String city;

    private String discrict;

    private String province;


    @Generated(hash = 257719200)
    public CityBean(Long id, String city, String discrict, String province) {
        this.id = id;
        this.city = city;
        this.discrict = discrict;
        this.province = province;
    }

    @Generated(hash = 273649691)
    public CityBean() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDiscrict() {
        return discrict;
    }

    public void setDiscrict(String discrict) {
        this.discrict = discrict;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
