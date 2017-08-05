package com.acbelter.weatherapp.domain.repository;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;

import java.util.List;

import io.reactivex.Flowable;

public interface DatabaseRepo {

    Flowable<List<CityData>> getAllCities();

    Flowable<FullWeatherModel> getWeatherByCityName(String cityName);

    void saveWeather(FullWeatherModel weatherModel);

    void updateWeather(FullWeatherModel weatherModel);

    void deleteAllWeatherRecords();
}
