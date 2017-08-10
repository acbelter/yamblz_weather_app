package com.acbelter.weatherapp.mvp.view.weather.details;

import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.mvp.view.common.BaseView;

public interface DetailView extends BaseView {

    void showWeather(FullWeatherModel fullWeatherModel);
}