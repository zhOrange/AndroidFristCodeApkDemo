package com.zh.orangeweather.gson;

import com.google.gson.annotations.SerializedName;

public class Basic {
    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;
}
