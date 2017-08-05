package com.acbelter.weatherapp.data.repository.database;

import com.acbelter.weatherapp.data.database.WeatherDAO;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.repository.DatabaseRepo;

import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.Flowable;

public class DatabaseRepoImpl implements DatabaseRepo {

    private WeatherDAO weatherDAO;
    private Executor executor;

    public DatabaseRepoImpl(WeatherDAO weatherDAO, Executor executor) {
        this.weatherDAO = weatherDAO;
        this.executor = executor;
    }

    @Override
    public Flowable<List<CityData>> getAllCities() {
        return weatherDAO.getAllWeatherRecords()
                .flatMapSingle(databaseWeatherDatas -> Flowable
                        .fromIterable(databaseWeatherDatas)
                        .map(DatabaseWeatherConverter::fromDatabaseWeatherDataToCityData)
                        .toList());
    }

    @Override
    public Flowable<FullWeatherModel> getWeatherByCityName(String cityName) {
        return weatherDAO.getWeatherByCityName(cityName)
                .map(DatabaseWeatherConverter::fromDatabaseWeatherDataToFullWeatherModel);
    }

    @Override
    public void saveWeather(FullWeatherModel weather) {
        weatherDAO.insertWeather(DatabaseWeatherConverter.fromFullWeatherDataToDatabaseFormat(weather));
    }

    @Override
    public void updateWeather(FullWeatherModel weatherModel) {
        weatherDAO.updateWeather(DatabaseWeatherConverter.fromFullWeatherDataToDatabaseFormat(weatherModel));
    }

    @Override
    public void deleteAllWeatherRecords() {
        weatherDAO.deleteAllWeatherRecords();
    }
}
