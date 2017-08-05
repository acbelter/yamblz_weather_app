package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;
import com.acbelter.weatherapp.domain.repository.DatabaseRepo;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import io.reactivex.Observable;
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

    public Observable<FullWeatherModel> getWeather() {
        return Observable
                .zip(getCurrentWeather(), getForecast(), this::convertCachedWeather)
                .doOnNext(fullWeatherModel -> databaseRepo.saveWeather(fullWeatherModel))
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    public Observable<FullWeatherModel> updateWeather() {
        return Observable
                .zip(updateCurrenWeather(), updateForecast(), this::convertUpdatedWeather)
                .doOnNext(fullWeatherModel -> databaseRepo.saveWeather(fullWeatherModel))
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    private Observable<CurrentWeatherData> getCurrentWeather() {
        return weatherRepo.getCurrentWeather();
    }

    private Observable<WeatherForecast> getForecast() {
        return weatherRepo.getForecast();
    }

    private Observable<CurrentWeatherData> updateCurrenWeather() {
        return weatherRepo.updateCurrentWeather();
    }

    private Observable<WeatherForecast> updateForecast() {
        return weatherRepo.updateForecast();
    }

    private FullWeatherModel convertCachedWeather(CurrentWeatherData currentWeatherData, WeatherForecast weatherForecast) {
        return new FullWeatherModel(currentWeatherData.getCityData(), currentWeatherData, weatherForecast);
    }

    private FullWeatherModel convertUpdatedWeather(CurrentWeatherData currentWeatherData, WeatherForecast weatherForecast) {
        return new FullWeatherModel(currentWeatherData.getCityData(), currentWeatherData, weatherForecast);
    }


    public void saveWeather(FullWeatherModel fullWeatherModel) {
        databaseRepo.saveWeather(fullWeatherModel);
    }
}
