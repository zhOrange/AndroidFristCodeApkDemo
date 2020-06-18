package com.zh.orangeweather.gson;

import com.google.gson.annotations.SerializedName;

public class Now {
    public class Cond{
        @SerializedName("txt")
        public String info;
    }
    @SerializedName("tmp")
    public String temerature;

    @SerializedName("cond")
    public Cond cond;
}
