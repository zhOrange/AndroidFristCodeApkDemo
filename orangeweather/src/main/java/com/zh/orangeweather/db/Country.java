package com.zh.orangeweather.db;

import org.litepal.crud.LitePalSupport;

/**
 * 数据库 Country表
 */
public class Country extends LitePalSupport {
    /**
     * id
     */
    private int id;
    /**
     * country名称
     */
    private String countryName;
    /**
     * 天气id
     */
    private String weatherId;
    /**
     * 所述city id.
     */
    private int cityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
