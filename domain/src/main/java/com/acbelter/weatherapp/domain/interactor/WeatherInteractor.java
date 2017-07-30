package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import javax.inject.Inject;

import io.reactivex.Observable;

public class WeatherInteractor {

    private WeatherRepo mWeatherRepo;

    @Inject
    public WeatherInteractor(WeatherRepo weatherRepo) {
        mWeatherRepo = weatherRepo;
    }

    public Observable<WeatherData> getCurrentWeather(WeatherParams params) {
        return mWeatherRepo.getCurrentWeather(params);
    }

    public void saveWeather(WeatherData weatherData) {
        mWeatherRepo.saveWeather(weatherData);
    }
}
