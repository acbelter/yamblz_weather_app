package com.acbelter.weatherapp.domain.repository;

import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

public interface DatabaseRepo {

    Flowable<List<FullWeatherModel>> getAllWeatherRecords();

    Maybe<FullWeatherModel> getWeatherByCityName(String cityName);

    void insertWeather(FullWeatherModel weather);

    void deleteAllWeatherRecords(String cityName);

    void deleteAllWeatherRecords();
}
