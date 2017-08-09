package com.acbelter.weatherapp.domain.repository;

import android.support.annotation.WorkerThread;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherFavorites;

import java.util.List;

import io.reactivex.Single;

public interface WeatherRepo {

    @WorkerThread
    Single<CurrentWeatherFavorites> getCurrentWeather();

    @WorkerThread
    Single<CurrentWeatherFavorites> updateCurrentWeather();

    @WorkerThread
    Single<List<ForecastWeatherFavorites>> getForecast();

    @WorkerThread
    Single<List<ForecastWeatherFavorites>> updateForecast();

    @WorkerThread
    void deleteWeather(CityData cityData);
}
