package com.acbelter.weatherapp.domain.repository;

import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import io.reactivex.Observable;

public interface WeatherRepo {
    Observable<WeatherData> getCurrentWeather(WeatherParams params);
}
