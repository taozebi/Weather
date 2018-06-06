package com.taoze.weather.model.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 聚合数据网天气信息entity
 * Created by Taoze on 2018/6/6.
 */

public class JuheWeather {

    private Sk sk;
    private Today today;
    private JsonObject future;
    private List<Future> futures;

    public Sk getSk() {
        return sk;
    }

    public void setSk(Sk sk) {
        this.sk = sk;
    }

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }

    public JsonObject getFuture() {
        return future;
    }

    public void setFuture(JsonObject future) {
        this.future = future;
    }

    public List<Future> getFutures() {
        if(futures == null){
            futures = new ArrayList<>();
            Gson gson = new GsonBuilder().create();
            Iterator<Map.Entry<String,JsonElement>> it = future.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String,JsonElement> entry = it.next();
                Future fobj = gson.fromJson(entry.getValue().toString(),Future.class);
                futures.add(fobj);
            }
        }
        return futures;
    }

    public void setFutures(List<Future> futures) {
        this.futures = futures;
    }

    /*当前实况天气*/
    public class Sk{
        /*当前温度*/
        private String temp;
        /*当前风向*/
        private String wind_direction;
        /*当前风力*/
        private String wind_strength;
        /*当前湿度*/
        private String humidity;
        /*更新时间*/
        private String time;

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getWind_direction() {
            return wind_direction;
        }

        public void setWind_direction(String wind_direction) {
            this.wind_direction = wind_direction;
        }

        public String getWind_strength() {
            return wind_strength;
        }

        public void setWind_strength(String wind_strength) {
            this.wind_strength = wind_strength;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public class Today{
        /*当前城市*/
        private String city;
        /*今日日期*/
        private String date_y;
        /*今日星期*/
        private String week;
        /*今日温度*/
        private String temperature;
        /*今日天气*/
        private String weather;
        /*天气唯一标识*/
        private Wid weather_id;
        /*风力风向 eg:南风3-4级*/
        private String wind;
        /*穿衣指数*/
        private String dressing_index;
        /*穿衣建议*/
        private String dressing_advice;
        /*紫外线强度*/
        private String uv_index;
        /*舒适度指数*/
        private String comfort_index;
        /*洗车指数*/
        private String wash_index;
        /*旅游指数*/
        private String travel_index;
        /*晨练指数*/
        private String exercise_index;
        /*干燥指数*/
        private String drying_index;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDate_y() {
            return date_y;
        }

        public void setDate_y(String date_y) {
            this.date_y = date_y;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public Wid getWeather_id() {
            return weather_id;
        }

        public void setWeather_id(Wid weather_id) {
            this.weather_id = weather_id;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public String getDressing_index() {
            return dressing_index;
        }

        public void setDressing_index(String dressing_index) {
            this.dressing_index = dressing_index;
        }

        public String getDressing_advice() {
            return dressing_advice;
        }

        public void setDressing_advice(String dressing_advice) {
            this.dressing_advice = dressing_advice;
        }

        public String getUv_index() {
            return uv_index;
        }

        public void setUv_index(String uv_index) {
            this.uv_index = uv_index;
        }

        public String getComfort_index() {
            return comfort_index;
        }

        public void setComfort_index(String comfort_index) {
            this.comfort_index = comfort_index;
        }

        public String getWash_index() {
            return wash_index;
        }

        public void setWash_index(String wash_index) {
            this.wash_index = wash_index;
        }

        public String getTravel_index() {
            return travel_index;
        }

        public void setTravel_index(String travel_index) {
            this.travel_index = travel_index;
        }

        public String getExercise_index() {
            return exercise_index;
        }

        public void setExercise_index(String exercise_index) {
            this.exercise_index = exercise_index;
        }

        public String getDrying_index() {
            return drying_index;
        }

        public void setDrying_index(String drying_index) {
            this.drying_index = drying_index;
        }
    }


    /*未来几天天气*/
    public class Future{
        /*温度*/
        private String temperature;
        /*天气*/
        private String weather;
        /*天气标识*/
        private Wid weather_id;
        /*风力风向*/
        private String wind;
        /*星期*/
        private String week;
        /*日期*/
        private String date;

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public Wid getWeather_id() {
            return weather_id;
        }

        public void setWeather_id(Wid weather_id) {
            this.weather_id = weather_id;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    /*天气唯一标识*/
    public class Wid{
        /*天气标识00：晴*/
        private String fa;
        /*天气标识53：霾 如果fa不等于fb，说明是组合天气*/
        private String fb;

        public String getFa() {
            return fa;
        }

        public void setFa(String fa) {
            this.fa = fa;
        }

        public String getFb() {
            return fb;
        }

        public void setFb(String fb) {
            this.fb = fb;
        }
    }
}
