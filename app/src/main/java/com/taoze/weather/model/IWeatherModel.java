package com.taoze.weather.model;

import com.taoze.weather.model.entity.Weather;
import com.taoze.weather.presenter.OnWeatherListener;

/**
 * Created by Taoze on 2018/6/1.
 */

public interface IWeatherModel {

    public void loadWeather(String cityCode, OnWeatherListener listener);
}
