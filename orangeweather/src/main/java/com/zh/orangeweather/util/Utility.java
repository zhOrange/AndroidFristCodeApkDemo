package com.zh.orangeweather.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.zh.orangeweather.db.City;
import com.zh.orangeweather.db.Country;
import com.zh.orangeweather.db.Province;
import com.zh.orangeweather.gson.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    /**
     * 处理省份查询请求response信息,将获得的Province添加到数据库中
     * @param response 返回response实体
     * @return
     */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allProvinces = new JSONArray(response);
                for(int i = 0; i < allProvinces.length(); i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 处理city请求response信息,将获得的city添加到数据库中
     * @param response 请求返回的response实体
     * @param provinceId 所属省份Province id.
     * @return
     */
    public static boolean handleCityResponse(String response, int provinceId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCities = new JSONArray(response);
                for(int i = 0; i < allCities.length(); i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 处理Country请求response信息,将获得的Country添加到数据库中
     * @param response 返回response实体
     * @param cityId 所属city id
     * @return
     */
    public static boolean handleCountryResponse(String response, int cityId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCountries = new JSONArray(response);
                for(int i = 0; i < allCountries.length(); i++){
                    JSONObject countryObject = allCountries.getJSONObject(i);
                    Country country = new Country();
                    country.setCountryName(countryObject.getString("name"));
                    country.setWeatherId(countryObject.getString("weather_id"));
                    country.setCityId(cityId);
                    country.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 处理天气查询数据
     * @param response 请求返回response实体
     * @return 解析得到的天气信息类.
     */
    public static Weather handleWeatherResponse(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            //利用Gson解析weatherContent
            return new Gson().fromJson(weatherContent, Weather.class);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

}
