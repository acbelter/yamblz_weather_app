package com.acbelter.weatherapp.data.database;

import android.content.Context;

import com.acbelter.weatherapp.data.dbmodel.DatabaseWeatherData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class DatabaseServiceImpl implements DatabaseService {
    public DatabaseServiceImpl(Context context) {

    }

    @Override
    public Observable<Long> insertWeatherData(DatabaseWeatherData weatherData) {
        return Observable.just(1L);
    }

    @Override
    public Observable<Integer> insertAllWeatherData(List<DatabaseWeatherData> weatherDataList) {
        return Observable.just(weatherDataList.size());
    }

    @Override
    public Observable<DatabaseWeatherData> getWeatherData(long id) {
        return Observable.just(new DatabaseWeatherData());
    }

    @Override
    public Observable<List<DatabaseWeatherData>> getAllWeatherData() {
        return Observable.just(new ArrayList<>(0));
    }

    @Override
    public Observable<Boolean> updateWeatherData(DatabaseWeatherData weatherData) {
        return Observable.just(true);
    }

    @Override
    public Observable<Boolean> deleteWeatherData(long id) {
        return Observable.just(true);
    }

    @Override
    public Observable<Integer> deleteAllWeatherData() {
        return Observable.just(0);
    }
}