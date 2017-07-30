package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class WeatherInteractor {

    private WeatherRepo mWeatherRepo;
    private Scheduler mSchedulerIO;

    @Inject
    public WeatherInteractor(WeatherRepo weatherRepo, Scheduler schedulersIO) {
        mWeatherRepo = weatherRepo;
        mSchedulerIO = schedulersIO;
    }

    public Observable<WeatherData> getCurrentWeather(WeatherParams params) {
        return mWeatherRepo.getCurrentWeather(params)
                .subscribeOn(mSchedulerIO)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void saveWeather(WeatherData weatherData) {
        mWeatherRepo.saveWeather(weatherData);
    }
}
