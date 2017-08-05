package com.acbelter.weatherapp.domain.repository;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface DatabaseRepo {

    Flowable<List<CityData>> getAllCities();

    Single<CurrentWeatherData> getCurrentWeather(WeatherParams weatherParams);

    Single<WeatherForecast> getForecastWeather(WeatherParams weatherParams);

    void saveWeather(FullWeatherModel weatherModel);

    void updateWeather(FullWeatherModel weatherModel);

    void deleteAllWeatherRecords();
}
