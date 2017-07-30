package com.acbelter.weatherapp.data.database;

import com.acbelter.weatherapp.data.dbmodel.DatabaseWeatherData;

import java.util.List;

import io.reactivex.Observable;

public interface DatabaseService {
    Observable<Long> insertWeatherData(DatabaseWeatherData weatherData);

    Observable<Integer> insertAllWeatherData(List<DatabaseWeatherData> weatherDataList);

    Observable<DatabaseWeatherData> getWeatherData(long id);

    Observable<List<DatabaseWeatherData>> getAllWeatherData();

    Observable<Boolean> updateWeatherData(DatabaseWeatherData weatherData);

    Observable<Boolean> deleteWeatherData(long id);

    Observable<Integer> deleteAllWeatherData();
}