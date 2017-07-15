package com.acbelter.weatherapp.data.database;

import android.content.Context;

import com.acbelter.weatherapp.data.dbmodel.DatabaseWeatherData;

import java.util.List;

import io.reactivex.Observable;

public class DatabaseServiceImpl implements DatabaseService {
    public DatabaseServiceImpl(Context context) {

    }

    @Override
    public Observable<Long> insertWeatherData(DatabaseWeatherData weatherData) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<Integer> insertAllWeatherData(List<DatabaseWeatherData> weatherDataList) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<DatabaseWeatherData> getWeatherData(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<List<DatabaseWeatherData>> getAllWeatherData() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<Boolean> updateWeatherData(DatabaseWeatherData weatherData) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<Boolean> deleteWeatherData(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<Integer> deleteAllWeatherData() {
        throw new UnsupportedOperationException();
    }
}