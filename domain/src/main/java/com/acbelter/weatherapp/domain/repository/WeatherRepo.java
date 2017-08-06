package com.acbelter.weatherapp.domain.repository;

import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;

import java.util.List;

import io.reactivex.Flowable;

public interface WeatherRepo {

    Flowable<CurrentWeatherData> getCurrentWeather();

    Flowable<CurrentWeatherData> updateCurrentWeather();

    Flowable<List<WeatherForecast>> getForecast();

    Flowable<List<WeatherForecast>> updateForecast();

    void saveWeather(FullWeatherModel fullWeatherModel);
}
