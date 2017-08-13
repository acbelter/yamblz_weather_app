package com.acbelter.weatherapp.mvp.view.weather;

import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.mvp.view.common.BaseView;

public interface WeatherView extends BaseView {

    void startLoading();

    void stopLoading();

    void showWeather(FullWeatherModel weatherData);

    void showError();
}
