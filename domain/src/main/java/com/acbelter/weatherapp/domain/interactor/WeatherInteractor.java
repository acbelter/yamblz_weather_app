package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class WeatherInteractor {

    private WeatherRepo mWeatherRepo;
    private Scheduler mSchedulerIO;
    private Scheduler mSchedulerMain;

    @Inject
    public WeatherInteractor(WeatherRepo weatherRepo, Scheduler schedulersIO, Scheduler schedulerMain) {
        mWeatherRepo = weatherRepo;
        mSchedulerIO = schedulersIO;
        mSchedulerMain = schedulerMain;
    }

    public Observable<WeatherData> getWeather() {
        return mWeatherRepo.getCurrentWeather()
                .subscribeOn(mSchedulerIO)
                .observeOn(mSchedulerMain);
    }

    public Observable<WeatherData> getCurrentWeather(WeatherParams params) {
        return null;
//        return mWeatherRepo.getCurrentWeather(params)
//                .subscribeOn(mSchedulerIO)
//                .observeOn(mSchedulerMain);
    }

    public void saveWeather(WeatherData weatherData) {
        mWeatherRepo.saveWeather(weatherData);
    }
}
