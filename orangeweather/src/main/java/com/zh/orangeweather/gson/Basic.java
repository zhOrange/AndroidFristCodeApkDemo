package com.zh.orangeweather.gson;

import com.google.gson.annotations.SerializedName;

public class Basic {
    public class Update{
        /**
         * 更新日期
         */
        @SerializedName("loc")
        public String updateTime;
    }

    /**
     * city名称
     */
    @SerializedName("city")
    public String cityName;

    /**
     * weather id
     */
    @SerializedName("id")
    public String weatherId;

    public Update update;
}
