package com.acbelter.weatherapp.domain.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherFavorites;
import com.acbelter.weatherapp.domain.repository.DatabaseRepo;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class WeatherInteractor {

    @NonNull
    private final WeatherRepo weatherRepo;
    @NonNull
    private final DatabaseRepo databaseRepo;

    @NonNull
    private final Scheduler schedulerIO;
    @NonNull
    private final Scheduler schedulerMain;

    public WeatherInteractor(@NonNull WeatherRepo weatherRepo, @NonNull DatabaseRepo databaseRepo
            , @NonNull Scheduler schedulersIO, @NonNull Scheduler schedulerMain) {
        this.weatherRepo = weatherRepo;
        this.databaseRepo = databaseRepo;
        this.schedulerIO = schedulersIO;
        this.schedulerMain = schedulerMain;

    }

    @WorkerThread
    public Single<FullWeatherModel> getWeather() {
        return Single
                .zip(getCurrentWeather(), getForecast(), this::convertCachedWeather)
                .doOnSuccess(databaseRepo::updateWeather)
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    @WorkerThread
    public Single<FullWeatherModel> updateWeather() {
        return Single
                .zip(updateCurrenWeather(), updateForecast(), this::convertUpdatedWeather)
                .doOnSuccess(databaseRepo::updateWeather)
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    @WorkerThread
    public Single<FullWeatherModel> getNewWeatherAndSaveToDB() {
        return Single
                .zip(updateCurrenWeather(), updateForecast(), this::convertUpdatedWeather)
                .doOnSuccess(databaseRepo::saveWeather)
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    @WorkerThread
    private Single<CurrentWeatherFavorites> getCurrentWeather() {
        return weatherRepo.getCurrentWeather();
    }

    @WorkerThread
    private Single<List<ForecastWeatherFavorites>> getForecast() {
        return weatherRepo.getForecast();
    }

    @WorkerThread
    private Single<CurrentWeatherFavorites> updateCurrenWeather() {
        return weatherRepo.updateCurrentWeather();
    }

    @WorkerThread
    private Single<List<ForecastWeatherFavorites>> updateForecast() {
        return weatherRepo.updateForecast();
    }

    @NonNull
    private FullWeatherModel convertCachedWeather(@NonNull CurrentWeatherFavorites currentWeatherFavorites, @NonNull List<ForecastWeatherFavorites> forecast) {
        return new FullWeatherModel(currentWeatherFavorites.getCityData(), currentWeatherFavorites, forecast);
    }

    @NonNull
    private FullWeatherModel convertUpdatedWeather(@NonNull CurrentWeatherFavorites currentWeatherFavorites, @NonNull List<ForecastWeatherFavorites> forecast) {
        return new FullWeatherModel(currentWeatherFavorites.getCityData(), currentWeatherFavorites, forecast);
    }

    public void saveWeather(@NonNull FullWeatherModel fullWeatherModel) {
        databaseRepo.saveWeather(fullWeatherModel);
    }
}
