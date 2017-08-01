package com.acbelter.weatherapp.domain.repository;

import com.acbelter.weatherapp.domain.model.weather.WeatherData;

import io.reactivex.Observable;

public interface WeatherRepo {

    Observable<WeatherData> getCurrentWeather();

    Observable<WeatherData> updateCurrentWeather();

    void saveWeather(WeatherData weatherData);
}
