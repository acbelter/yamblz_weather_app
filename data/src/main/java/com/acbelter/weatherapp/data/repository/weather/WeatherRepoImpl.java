package com.acbelter.weatherapp.data.repository.weather;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.acbelter.weatherapp.data.network.NetworkRepo;
import com.acbelter.weatherapp.data.repository.preference.SettingsPreference;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.repository.DatabaseRepo;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import timber.log.Timber;

public class WeatherRepoImpl implements WeatherRepo {

    @NonNull
    private final NetworkRepo networkRepo;
    @NonNull
    private final SettingsPreference settingsPreference;
    @NonNull
    private final DatabaseRepo databaseRepo;

    public WeatherRepoImpl(@NonNull NetworkRepo networkRepo, @NonNull SettingsPreference settingsPreference, @NonNull DatabaseRepo databaseRepo) {
        this.networkRepo = networkRepo;
        this.settingsPreference = settingsPreference;
        this.databaseRepo = databaseRepo;
    }

    @Override
    @WorkerThread
    public Single<CurrentWeatherFavorites> getCurrentWeather() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return databaseRepo.getCurrentWeather(weatherParams.getCityData())
                .map(currentWeatherFavorites ->
                        WeatherDataConverter.updateCurrentWeatherMetric(currentWeatherFavorites, weatherParams.getMetric()))
                .doOnSuccess(data -> Timber.v("Current data from DB: %s", data))
                .onErrorResumeNext(networkRepo.getCurrentWeather(weatherParams)
                        .map(currentWeather ->
                                WeatherDataConverter
                                        .fromNWWeatherDataToCurrentWeatherData(currentWeather, weatherParams)))
                .doOnSuccess(data -> Timber.d("Current weather data from network: %s", data));
    }

    @Override
    @WorkerThread
    public Single<List<ForecastWeatherFavorites>> getForecast() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return databaseRepo.getForecastWeather(weatherParams.getCityData())
                .flatMap(forecastList -> Observable.fromIterable(forecastList)
                        .map(element -> WeatherDataConverter
                                .updateForecastWeatherMetric(element, weatherParams.getMetric()))
                        .toList())
                .doOnSuccess(data -> Timber.d("Forecast weather data from DB: %s", data))
                .onErrorResumeNext(networkRepo.getForecastWeather(weatherParams)
                        .map(forecastWeather -> WeatherDataConverter.fromNWWeatherDataToForecastWeatherData(forecastWeather, weatherParams)))
                .doOnSuccess(data -> Timber.d("Forecast weather data from network: %s", data));
    }

    @Override
    @WorkerThread
    public Single<CurrentWeatherFavorites> updateCurrentWeather() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return networkRepo.getCurrentWeather(weatherParams)
                .map(currentWeather -> WeatherDataConverter.fromNWWeatherDataToCurrentWeatherData(currentWeather, weatherParams));
    }

    @Override
    @WorkerThread
    public Single<List<ForecastWeatherFavorites>> updateForecast() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return networkRepo.getForecastWeather(weatherParams)
                .map(extendedWeather -> WeatherDataConverter
                        .fromNWWeatherDataToForecastWeatherData(extendedWeather, weatherParams));
    }

    @Override
    @WorkerThread
    public void deleteWeather(CityData cityData) {
        databaseRepo.deleteWeather(cityData);
    }
}
