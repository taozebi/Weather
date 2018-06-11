package com.taoze.weather.model;

import com.taoze.weather.model.entity.Weather;
import com.taoze.weather.presenter.OnWeatherListener;

/**
 * Weather获取数据接口定义
 * Created by Taoze on 2018/6/1.
 */

public interface IWeatherModel extends IBaseModel{

    public void loadWeather(String cityCode, OnWeatherListener listener);

}
