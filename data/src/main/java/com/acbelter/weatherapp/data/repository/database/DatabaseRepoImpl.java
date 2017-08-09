package com.acbelter.weatherapp.data.repository.database;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

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

import io.reactivex.Flowable;
import io.reactivex.Single;
import timber.log.Timber;

public class DatabaseRepoImpl implements DatabaseRepo {

    @NonNull
    private WeatherDAO weatherDAO;

    public DatabaseRepoImpl(@NonNull WeatherDAO weatherDAO) {
        this.weatherDAO = weatherDAO;
    }

    @Override
    @WorkerThread
    public Flowable<List<CityData>> getAllCities() {
        return weatherDAO.getAllWeatherRecords()
                .flatMapSingle(databaseWeatherDatas -> Flowable
                        .fromIterable(databaseWeatherDatas)
                        .map(DatabaseWeatherConverter::fromDatabaseWeatherDataToCityData)
                        .toList());
    }

    @Override
    @WorkerThread
    public Single<CurrentWeatherFavorites> getCurrentWeather(@NonNull WeatherParams weatherParams) {
        double latitude = weatherParams.getCityData().getLatitude();
        double longitude = weatherParams.getCityData().getLongitude();
        Coord coord = new Coord(latitude, longitude);
        return weatherDAO.getWeatherByCityName(new Gson().toJson(coord))
                .doOnError(throwable -> Timber.v("Can't get data from DB = " + throwable.toString()))
                .map(DatabaseWeatherConverter::fromDatabaseWeatherDataToCurrentWeatherData);
    }

    @Override
    @WorkerThread
    public Single<List<ForecastWeatherFavorites>> getForecastWeather(@NonNull WeatherParams weatherParams) {
        double latitude = weatherParams.getCityData().getLatitude();
        double longitude = weatherParams.getCityData().getLongitude();
        Coord coord = new Coord(latitude, longitude);
        return weatherDAO.getWeatherByCityName(new Gson().toJson(coord))
                .map(DatabaseWeatherConverter::fromDatabaseWeatherDataToForecastWeather);
    }

    @Override
    @MainThread
    public void saveWeather(@NonNull FullWeatherModel weather) {
        weatherDAO.insertWeather(DatabaseWeatherConverter.fromFullWeatherDataToDatabaseFormat(weather));
    }

    @Override
    @MainThread
    public void updateWeather(@NonNull FullWeatherModel weatherModel) {
        weatherDAO.updateWeather(DatabaseWeatherConverter.fromFullWeatherDataToDatabaseFormat(weatherModel));
    }
}
