package com.taoze.weather.model;

import com.taoze.weather.model.entity.Weather;

/**
 * Created by Taoze on 2018/6/1.
 */

public interface IWeatherModel {

    public Weather getWeather(String cityName);
}
