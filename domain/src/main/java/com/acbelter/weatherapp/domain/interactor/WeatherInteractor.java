package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class WeatherInteractor {

    private WeatherRepo weatherRepo;
    private Scheduler schedulerIO;
    private Scheduler schedulerMain;

    public WeatherInteractor(WeatherRepo weatherRepo, Scheduler schedulersIO, Scheduler schedulerMain) {
        this.weatherRepo = weatherRepo;
        schedulerIO = schedulersIO;
        this.schedulerMain = schedulerMain;
    }

    public Observable<WeatherData> getWeather() {
        return weatherRepo.getCurrentWeather()
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    public Observable<WeatherData> updateWeather() {
        return weatherRepo.updateCurrentWeather()
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    public void saveWeather(WeatherData weatherData) {
        weatherRepo.saveWeather(weatherData);
    }
}
