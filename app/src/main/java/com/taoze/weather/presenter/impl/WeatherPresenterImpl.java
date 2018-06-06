package com.taoze.weather.presenter.impl;

import com.taoze.weather.model.IWeatherModel;
import com.taoze.weather.model.entity.JuheWeather;
import com.taoze.weather.model.entity.Weather;
import com.taoze.weather.model.impl.WeatherModelImpl;
import com.taoze.weather.presenter.IWeatherPresenter;
import com.taoze.weather.presenter.OnWeatherListener;
import com.taoze.weather.ui.view.IWeatherView;

/**
 * 天气主导者
 * Created by Taoze on 2018/6/5.
 */
public class WeatherPresenterImpl implements IWeatherPresenter,OnWeatherListener{

    private IWeatherModel weatherModel;
    private IWeatherView weatherView;

    public WeatherPresenterImpl(IWeatherView weatherView) {
        this.weatherView = weatherView;
        weatherModel = new WeatherModelImpl();
    }

    @Override
    public void getWeather(String cityNO) {
        weatherModel.loadWeather(cityNO,this);
    }

    @Override
    public void onSuccess(Weather weather) {
        weatherView.setWeather(weather);
    }

    @Override
    public void onError(String errorMsg) {
        weatherView.showError(errorMsg);
    }

    @Override
    public void onSuccess(JuheWeather weather) {
        weatherView.setWeather(weather);
    }
}
