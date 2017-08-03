package com.acbelter.weatherapp.domain.repository;

import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;

import io.reactivex.Observable;

public interface WeatherRepo {

    Observable<WeatherData> getCurrentWeather();

    Observable<WeatherData> updateCurrentWeather();

    Observable<WeatherForecast> getForecast();

    Observable<WeatherForecast> updateForecast();

    void saveWeather(WeatherData weatherData);
}
