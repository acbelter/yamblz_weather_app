package com.acbelter.weatherapp.domain.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface DatabaseRepo {

    @WorkerThread
    Flowable<List<CityData>> getAllCities();

    @WorkerThread
    Single<CurrentWeatherFavorites> getCurrentWeather(@NonNull WeatherParams weatherParams);

    @WorkerThread
    Single<List<ForecastWeatherFavorites>> getForecastWeather(@NonNull WeatherParams weatherParams);

    @MainThread
    void saveWeather(@NonNull FullWeatherModel weatherModel);

    @MainThread
    void updateWeather(@NonNull FullWeatherModel weatherModel);
}