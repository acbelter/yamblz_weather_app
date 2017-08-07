package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherFavorites;
import com.acbelter.weatherapp.domain.repository.DatabaseRepo;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class WeatherInteractor {

    private WeatherRepo weatherRepo;
    private DatabaseRepo databaseRepo;

    private Scheduler schedulerIO;
    private Scheduler schedulerMain;

    public WeatherInteractor(WeatherRepo weatherRepo, DatabaseRepo databaseRepo, Scheduler schedulersIO, Scheduler schedulerMain) {
        this.weatherRepo = weatherRepo;
        this.databaseRepo = databaseRepo;
        this.schedulerIO = schedulersIO;
        this.schedulerMain = schedulerMain;

    }

    public Single<FullWeatherModel> getWeather() {
        return Single
                .zip(getCurrentWeather(), getForecast(), this::convertCachedWeather)
                .doOnSuccess(fullWeatherModel -> databaseRepo.saveWeather(fullWeatherModel))
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    public Single<FullWeatherModel> updateWeather() {
        return Single
                .zip(updateCurrenWeather(), updateForecast(), this::convertUpdatedWeather)
                .doOnSuccess(fullWeatherModel -> databaseRepo.saveWeather(fullWeatherModel))
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    private Single<CurrentWeatherFavorites> getCurrentWeather() {
        return weatherRepo.getCurrentWeather();
    }

    private Single<List<ForecastWeatherFavorites>> getForecast() {
        return weatherRepo.getForecast();
    }

    private Single<CurrentWeatherFavorites> updateCurrenWeather() {
        return weatherRepo.updateCurrentWeather();
    }

    private Single<List<ForecastWeatherFavorites>> updateForecast() {
        return weatherRepo.updateForecast();
    }

    private FullWeatherModel convertCachedWeather(CurrentWeatherFavorites currentWeatherFavorites, List<ForecastWeatherFavorites> forecast) {
        return new FullWeatherModel(currentWeatherFavorites.getCityData(), currentWeatherFavorites, forecast);
    }

    private FullWeatherModel convertUpdatedWeather(CurrentWeatherFavorites currentWeatherFavorites, List<ForecastWeatherFavorites> forecast) {
        return new FullWeatherModel(currentWeatherFavorites.getCityData(), currentWeatherFavorites, forecast);
    }

    public void saveWeather(FullWeatherModel fullWeatherModel) {
        databaseRepo.saveWeather(fullWeatherModel);
    }
}
