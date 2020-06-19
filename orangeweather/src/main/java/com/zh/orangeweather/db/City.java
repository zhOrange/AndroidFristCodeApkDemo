package com.zh.orangeweather.db;

import org.litepal.crud.LitePalSupport;

/**
 * 数据库 City表.
 */
public class City extends LitePalSupport {
    /**
     * id
     */
    private int id;
    /**
     * City 名称
     */
    private String cityName;
    /**
     * City 代号
     */
    private int cityCode;
    /**
     * 所属province代号
     */
    private int provinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
