package com.taoze.weather.model.impl;

import android.util.Log;

import com.google.gson.Gson;
import com.taoze.weather.model.IWeatherModel;
import com.taoze.weather.model.entity.Weather;
import com.taoze.weather.presenter.OnWeatherListener;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;

/**
 * Created by Taoze on 2018/6/1.
 */

public class WeatherModelImpl implements IWeatherModel {

    private Weather weather;

    @Override
    public void loadWeather(String cityNO, final OnWeatherListener listener) {
        String url1 = "http://www.weather.com.cn/data/sk/" + cityNO + ".html";
        String url2 = "http://www.weather.com.cn/data/cityinfo/" + cityNO + ".html";
        HttpRequest.get(url1 ,null, 15000, new BaseHttpRequestCallback() {
                    @Override
                    protected void onSuccess(Object o) {
                        super.onSuccess(o);
                        if(weather == null){
                            weather = new Gson().fromJson(o.toString(), Weather.class);
                        }else{
                            Weather temp= new Gson().fromJson(o.toString(), Weather.class);
                            weather.getWeatherinfo().setTemp(temp.getWeatherinfo().getTemp());
                            weather.getWeatherinfo().setWD(temp.getWeatherinfo().getWD());
                            weather.getWeatherinfo().setWS(temp.getWeatherinfo().getWS());
                            weather.getWeatherinfo().setSD(temp.getWeatherinfo().getSD());
                            weather.getWeatherinfo().setWSE(temp.getWeatherinfo().getWSE());
                            weather.getWeatherinfo().setNjd(temp.getWeatherinfo().getNjd());
                            weather.getWeatherinfo().setRain(temp.getWeatherinfo().getRain());
                            weather.getWeatherinfo().setTime(temp.getWeatherinfo().getTime());
                        }
                        if (weather != null) {
                            listener.onSuccess(weather);
                        } else {
                            listener.onError("请求结果数据为空");
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String msg) {
                        super.onFailure(errorCode, msg);
                        listener.onError(msg);
                    }
                }
        );
        HttpRequest.get(url1 ,null, 15000, new BaseHttpRequestCallback() {
                    @Override
                    protected void onSuccess(Object o) {
                        super.onSuccess(o);
                        if(weather == null){
                            weather = new Gson().fromJson(o.toString(), Weather.class);
                        }else{
                            Weather temp= new Gson().fromJson(o.toString(), Weather.class);
                            weather.getWeatherinfo().setWeather(temp.getWeatherinfo().getWeather());
                            weather.getWeatherinfo().setTemp1(temp.getWeatherinfo().getTemp1());
                            weather.getWeatherinfo().setTemp2(temp.getWeatherinfo().getTemp2());
                            weather.getWeatherinfo().setPtime(temp.getWeatherinfo().getPtime());
                        }
                        if (weather != null) {
                            listener.onSuccess(weather);
                        } else {
                            listener.onError("请求结果数据为空");
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String msg) {
                        super.onFailure(errorCode, msg);
                        listener.onError(msg);
                    }
                }
        );
    }
}
