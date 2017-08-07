package com.acbelter.weatherapp.domain.repository;

import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherFavorites;

import java.util.List;

import io.reactivex.Single;

public interface WeatherRepo {

    Single<CurrentWeatherFavorites> getCurrentWeather();

    Single<CurrentWeatherFavorites> updateCurrentWeather();

    Single<List<ForecastWeatherFavorites>> getForecast();

    Single<List<ForecastWeatherFavorites>> updateForecast();

    void saveWeather(FullWeatherModel fullWeatherModel);
}
