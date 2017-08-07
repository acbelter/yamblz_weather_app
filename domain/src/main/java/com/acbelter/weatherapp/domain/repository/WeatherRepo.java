package com.acbelter.weatherapp.domain.repository;

import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherFavorites;

import java.util.List;

import io.reactivex.Flowable;

public interface WeatherRepo {

    Flowable<CurrentWeatherFavorites> getCurrentWeather();

    Flowable<CurrentWeatherFavorites> updateCurrentWeather();

    Flowable<List<ForecastWeatherFavorites>> getForecast();

    Flowable<List<ForecastWeatherFavorites>> updateForecast();

    void saveWeather(FullWeatherModel fullWeatherModel);
}
