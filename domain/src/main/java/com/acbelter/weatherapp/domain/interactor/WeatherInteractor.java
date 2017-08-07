package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherFavorites;
import com.acbelter.weatherapp.domain.repository.DatabaseRepo;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;

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

    public Flowable<FullWeatherModel> getWeather() {
        return Flowable
                .zip(getCurrentWeather(), getForecast(), this::convertCachedWeather)
                .doOnNext(fullWeatherModel -> databaseRepo.saveWeather(fullWeatherModel))
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    public Flowable<FullWeatherModel> updateWeather() {
        return Flowable
                .zip(updateCurrenWeather(), updateForecast(), this::convertUpdatedWeather)
                .doOnNext(fullWeatherModel -> databaseRepo.saveWeather(fullWeatherModel))
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    private Flowable<CurrentWeatherFavorites> getCurrentWeather() {
        return weatherRepo.getCurrentWeather();
    }

    private Flowable<List<ForecastWeatherFavorites>> getForecast() {
        return weatherRepo.getForecast();
    }

    private Flowable<CurrentWeatherFavorites> updateCurrenWeather() {
        return weatherRepo.updateCurrentWeather();
    }

    private Flowable<List<ForecastWeatherFavorites>> updateForecast() {
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
