package com.acbelter.weatherapp.domain.repository;

import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;

import io.reactivex.Flowable;

public interface WeatherRepo {

    Flowable<CurrentWeatherData> getCurrentWeather();

    Flowable<CurrentWeatherData> updateCurrentWeather();

    Flowable<WeatherForecast> getForecast();

    Flowable<WeatherForecast> updateForecast();

    void saveWeather(CurrentWeatherData currentWeatherData);
}
