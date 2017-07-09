package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.WeatherData;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class WeatherInteractor {
    private WeatherRepo mWeatherRepo;

    @Inject
    public WeatherInteractor(WeatherRepo weatherRepo) {
        mWeatherRepo = weatherRepo;
    }

    public Observable<List<WeatherData>> getWeather() {
        return mWeatherRepo.getWeather();
    }
}
