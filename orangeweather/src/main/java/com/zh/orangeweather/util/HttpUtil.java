package com.zh.orangeweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    /**
     * 基于okhttp3实现数据请求和回调.
     * @param address 请求地址,API28之后,不再默认支持http请求,需要写配置文件将此限制取消.
     * @param callback 请求回调函数,包括请求失败或成功的处理.
     */
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback){
        /**
         * 请求client
         */
        OkHttpClient client = new OkHttpClient();
        /**
         * 请求内容设置
         */
        Request request = new Request.Builder()
                .url(address)
                .build();
        //发起请求,配置回调方法.
        client.newCall(request).enqueue(callback);
    }

}
