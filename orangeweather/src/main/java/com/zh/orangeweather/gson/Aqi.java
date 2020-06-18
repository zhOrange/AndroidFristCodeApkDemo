package com.zh.orangeweather.gson;

import com.google.gson.annotations.SerializedName;

public class Aqi {
    public AqiCity city;
    public class AqiCity{
        @SerializedName("aqi")
        public String aqi;
        @SerializedName("pm25")
        public String pm25;
    }
}
