package com.acbelter.weatherapp.data.repository.database;

import com.acbelter.weatherapp.data.database.WeatherDAO;
import com.acbelter.weatherapp.data.weathermodel.common.Coord;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.repository.DatabaseRepo;
import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.Observable;
import io.reactivex.Single;

public class DatabaseRepoImpl implements DatabaseRepo {

    private WeatherDAO weatherDAO;
    private Executor executor;

    public DatabaseRepoImpl(WeatherDAO weatherDAO, Executor executor) {
        this.weatherDAO = weatherDAO;
        this.executor = executor;
    }

    @Override
    public Single<List<CityData>> getAllCities() {
        return weatherDAO.getAllWeatherRecords()
                .flatMap(databaseWeatherDatas -> Observable
                        .fromIterable(databaseWeatherDatas)
                        .map(DatabaseWeatherConverter::fromDatabaseWeatherDataToCityData)
                        .toList());
    }

    @Override
    public Single<CurrentWeatherFavorites> getCurrentWeather(WeatherParams weatherParams) {
        double latitude = weatherParams.getCityData().getLatitude();
        double longitude = weatherParams.getCityData().getLongitude();
        Coord coord = new Coord(latitude, longitude);
        return weatherDAO.getWeatherByCityName(new Gson().toJson(coord))
                .map(DatabaseWeatherConverter::fromDatabaseWeatherDataToCurrentWeatherData);
    }

    @Override
    public Single<List<ForecastWeatherFavorites>> getForecastWeather(WeatherParams weatherParams) {
        double latitude = weatherParams.getCityData().getLatitude();
        double longitude = weatherParams.getCityData().getLongitude();
        Coord coord = new Coord(latitude, longitude);
        return weatherDAO.getWeatherByCityName(new Gson().toJson(coord))
                .map(DatabaseWeatherConverter::fromDatabaseWeatherDataToForecastWeather);
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
