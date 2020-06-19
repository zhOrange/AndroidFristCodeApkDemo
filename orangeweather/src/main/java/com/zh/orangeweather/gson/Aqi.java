package com.zh.orangeweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Gson解析json数据中的Aqi数据.
 */
public class Aqi {
    public AqiCity city;
    public class AqiCity{
        /**
         * aqi指数
         */
        @SerializedName("aqi")
        public String aqi;
        /**
         * pm25指数
         */
        @SerializedName("pm25")
        public String pm25;
    }
}
