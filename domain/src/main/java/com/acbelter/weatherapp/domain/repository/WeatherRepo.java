package com.acbelter.weatherapp.domain.repository;

import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;

import io.reactivex.Observable;

public interface WeatherRepo {

    Observable<CurrentWeatherData> getCurrentWeather();

    Observable<CurrentWeatherData> updateCurrentWeather();

    Observable<WeatherForecast> getForecast();

    Observable<WeatherForecast> updateForecast();

    void saveWeather(CurrentWeatherData currentWeatherData);
}
