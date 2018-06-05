package com.taoze.weather.presenter;

/**
 * 天气管理接口定义
 * Created by Taoze on 2018/5/31.
 */

public interface IWeatherPresenter {
    /**
     * 获取天气的逻辑
     */
    void getWeather(String cityNO);
}
