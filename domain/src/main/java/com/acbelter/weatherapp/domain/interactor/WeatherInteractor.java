package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;
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

    private Flowable<CurrentWeatherData> getCurrentWeather() {
        return weatherRepo.getCurrentWeather();
    }

    private Flowable<List<WeatherForecast>> getForecast() {
        return weatherRepo.getForecast();
    }

    private Flowable<CurrentWeatherData> updateCurrenWeather() {
        return weatherRepo.updateCurrentWeather();
    }

    private Flowable<List<WeatherForecast>> updateForecast() {
        return weatherRepo.updateForecast();
    }

    private FullWeatherModel convertCachedWeather(CurrentWeatherData currentWeatherData, List<WeatherForecast> forecast) {
        return new FullWeatherModel(currentWeatherData.getCityData(), currentWeatherData, forecast);
    }

    private FullWeatherModel convertUpdatedWeather(CurrentWeatherData currentWeatherData, List<WeatherForecast> forecast) {
        return new FullWeatherModel(currentWeatherData.getCityData(), currentWeatherData, forecast);
    }


    public void saveWeather(FullWeatherModel fullWeatherModel) {
        databaseRepo.saveWeather(fullWeatherModel);
    }
}
