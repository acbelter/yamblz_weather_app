package com.acbelter.weatherapp.domain.repository;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import java.util.List;

import io.reactivex.Single;

public interface DatabaseRepo {

    Single<List<CityData>> getAllCities();

    Single<CurrentWeatherFavorites> getCurrentWeather(WeatherParams weatherParams);

    Single<List<ForecastWeatherFavorites>> getForecastWeather(WeatherParams weatherParams);

    void saveWeather(FullWeatherModel weatherModel);

    void updateWeather(FullWeatherModel weatherModel);

    void deleteAllWeatherRecords();
}
