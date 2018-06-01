package com.taoze.weather.presenter;

import com.taoze.weather.model.entity.Weather;

/**
 *
 * Created by Taoze on 2018/6/1.
 */

public interface OnWeatherListener {
    public void onSuccess(Weather weather);
    public void onError(String errorMsg);
}
