package com.zh.orangeweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {
    /**
     * response返回状态信息
     */
    public String status;
    /**
     * 基础信息
     */
    public Basic basic;
    public Aqi aqi;
    public Now now;
    public Suggestion suggestion;
    /**
     * 未来天气信息
     */
    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;
}
