package com.acbelter.weatherapp.domain.repository;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherElement;

import java.util.List;

import io.reactivex.Single;

public interface DatabaseRepo {

    @WorkerThread
    Single<List<CityData>> getAllCities();

    @WorkerThread
    Single<CurrentWeatherFavorites> getCurrentWeather(@NonNull CityData cityData);

    @WorkerThread
    Single<List<ForecastWeatherElement>> getForecastWeather(@NonNull CityData cityData);

    @WorkerThread
    void saveWeather(@NonNull FullWeatherModel weatherModel);

    @WorkerThread
    void updateWeather(@NonNull FullWeatherModel weatherModel);

    @WorkerThread
    void deleteWeather(@NonNull CityData cityData);
}