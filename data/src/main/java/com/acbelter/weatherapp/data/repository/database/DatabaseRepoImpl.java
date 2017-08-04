package com.acbelter.weatherapp.data.repository.database;

import com.acbelter.weatherapp.data.database.WeatherDAO;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.repository.DatabaseRepo;

import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

public class DatabaseRepoImpl implements DatabaseRepo {

    WeatherDAO weatherDAO;
    Executor executor;

    public DatabaseRepoImpl(WeatherDAO weatherDAO, Executor executor) {
        this.weatherDAO = weatherDAO;
        this.executor = executor;
    }

    @Override
    public Flowable<List<FullWeatherModel>> getAllWeatherRecords() {
        return weatherDAO.getAllWeatherRecords()
                .flatMapSingle(databaseWeatherDatas -> Flowable
                        .fromIterable(databaseWeatherDatas)
                        .map(DatabaseWeatherConverter::fullWeatherFromDatabase)
                        .toList());
    }

    @Override
    public Maybe<FullWeatherModel> getWeatherByCityName(String cityName) {
        return null;
    }

    @Override
    public void insertWeather(FullWeatherModel weather) {
        weatherDAO.insertWeather(DatabaseWeatherConverter.fromFullWeatherDataToDatabaseFormat(weather));
    }

    @Override
    public void deleteAllWeatherRecords(String cityName) {

    }

    @Override
    public void deleteAllWeatherRecords() {

    }
}
