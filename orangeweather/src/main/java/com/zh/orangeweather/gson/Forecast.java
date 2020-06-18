package com.zh.orangeweather.gson;

import com.google.gson.annotations.SerializedName;

public class Forecast {
    public class Cond{
        @SerializedName("txt_d")
        public String info;
    }
    public class Temperature{
        @SerializedName("max")
        public String maxT;
        @SerializedName("min")
        public String minT;
    }

    @SerializedName("date")
    public String date;
    @SerializedName("cond")
    public Cond cond;

    @SerializedName("tmp")
    public Temperature temperature;

}
