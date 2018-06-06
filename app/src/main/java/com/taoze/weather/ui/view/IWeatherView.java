package com.taoze.weather.ui.view;

import com.taoze.weather.model.entity.JuheWeather;
import com.taoze.weather.model.entity.Weather;

/**
 * Created by Taoze on 2018/6/1.
 */

public interface IWeatherView {
    void showError(String errorMsg);
    void setWeather(Weather weather);
    void setWeather(JuheWeather juheWeather);
}
