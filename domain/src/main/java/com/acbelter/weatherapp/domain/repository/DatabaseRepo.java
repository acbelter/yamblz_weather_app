package com.acbelter.weatherapp.domain.repository;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

public interface DatabaseRepo {

    Flowable<List<CityData>> getAllCities();

    Maybe<FullWeatherModel> getWeatherByCityName(String cityName);

    void saveWeather(FullWeatherModel weatherModel);

    void updateWeather(FullWeatherModel weatherModel);

    void deleteAllWeatherRecords();
}
