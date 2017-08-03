package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.settings.SettingsData;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class WeatherInteractor {

    private WeatherRepo weatherRepo;

    private Scheduler schedulerIO;
    private Scheduler schedulerMain;

    public WeatherInteractor(WeatherRepo weatherRepo, Scheduler schedulersIO, Scheduler schedulerMain) {
        this.weatherRepo = weatherRepo;
        this.schedulerIO = schedulersIO;
        this.schedulerMain = schedulerMain;

    }

    public Observable<FullWeatherModel> getWeather() {
        return Observable.zip(getCurrentWeather(), getForecast(), this::convertCachedWeather);
    }

    public Observable<FullWeatherModel> updateWeather() {
        return Observable.zip(updateCurrenWeather(), updateForecast(), this::convertUpdatedWeather)
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    private Observable<WeatherData> getCurrentWeather() {
        return weatherRepo.getCurrentWeather()
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    private Observable<WeatherForecast> getForecast() {
        return weatherRepo.getForecast()
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    private Observable<WeatherData> updateCurrenWeather() {
        return weatherRepo.updateCurrentWeather()
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    private Observable<WeatherForecast> updateForecast() {
        return weatherRepo.updateForecast()
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    private FullWeatherModel convertCachedWeather(WeatherData weatherData, WeatherForecast weatherForecast) {
        return new FullWeatherModel(weatherData.getCity(), weatherData, weatherForecast);
    }

    private FullWeatherModel convertUpdatedWeather(WeatherData weatherData, WeatherForecast weatherForecast) {
        return new FullWeatherModel(weatherData.getCity(), weatherData, weatherForecast);
    }


    public void saveWeather(WeatherData weatherData) {
        weatherRepo.saveWeather(weatherData);
    }
}
