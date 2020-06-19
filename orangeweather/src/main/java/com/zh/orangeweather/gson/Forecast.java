package com.zh.orangeweather.gson;

import com.google.gson.annotations.SerializedName;

public class Forecast {
    public class Cond{
        /**
         * 天气信息
         */
        @SerializedName("txt_d")
        public String info;
    }
    public class Temperature{
        /**
         * 最高温度
         */
        @SerializedName("max")
        public String maxT;
        /**
         * 最低温度
         */
        @SerializedName("min")
        public String minT;
    }

    /**
     * 日期
     */
    @SerializedName("date")
    public String date;
    @SerializedName("cond")
    public Cond cond;

    /**
     * 温度信息
     */
    @SerializedName("tmp")
    public Temperature temperature;

}
