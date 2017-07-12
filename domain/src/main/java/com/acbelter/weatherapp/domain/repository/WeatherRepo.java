package com.acbelter.weatherapp.domain.repository;

import com.acbelter.weatherapp.domain.model.WeatherData;

import java.util.List;

import io.reactivex.Observable;

public interface WeatherRepo {
    Observable<List<WeatherData>> getWeather();
}
