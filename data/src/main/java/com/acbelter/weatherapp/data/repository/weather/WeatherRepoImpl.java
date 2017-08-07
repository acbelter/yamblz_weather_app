package com.acbelter.weatherapp.data.repository.weather;

import com.acbelter.weatherapp.data.network.NetworkRepo;
import com.acbelter.weatherapp.data.repository.preference.SettingsPreference;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.repository.DatabaseRepo;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import java.util.List;

import io.reactivex.Flowable;
import timber.log.Timber;

public class WeatherRepoImpl implements WeatherRepo {

    private NetworkRepo networkRepo;
    private SettingsPreference settingsPreference;
    private DatabaseRepo databaseRepo;

    public WeatherRepoImpl(NetworkRepo networkRepo, SettingsPreference settingsPreference, DatabaseRepo databaseRepo) {
        this.networkRepo = networkRepo;
        this.settingsPreference = settingsPreference;
        this.databaseRepo = databaseRepo;
    }

    @Override
    public Flowable<CurrentWeatherFavorites> getCurrentWeather() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return databaseRepo.getCurrentWeather(weatherParams)
                .toFlowable()
                .onErrorResumeNext(networkRepo.getCurrentWeather(weatherParams)
                        .map(currentWeather -> WeatherDataConverter.fromNWWeatherDataToCurrentWeatherData(currentWeather, weatherParams))
                        .toFlowable())
                .doOnNext(data -> {
                    Timber.d("Current weather data from network: %s", data);
                });
    }

    @Override
    public Flowable<List<ForecastWeatherFavorites>> getForecast() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return databaseRepo.getForecastWeather(weatherParams)
                .toFlowable()
                .onErrorResumeNext(networkRepo.getForecastWeather(weatherParams)
                        .map(forecastWeather -> WeatherDataConverter.fromNWWeatherDataToForecastWeatherData(forecastWeather, weatherParams))
                        .toFlowable())
                .doOnNext(data -> {
                    Timber.d("Current weather data from network: %s", data);
                });
    }

    @Override
    public Flowable<CurrentWeatherFavorites> updateCurrentWeather() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return networkRepo.getCurrentWeather(weatherParams)
                .map(currentWeather -> WeatherDataConverter.fromNWWeatherDataToCurrentWeatherData(currentWeather, weatherParams))
                .toFlowable();
    }

    @Override
    public Flowable<List<ForecastWeatherFavorites>> updateForecast() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return networkRepo.getForecastWeather(weatherParams)
                .map(extendedWeather -> WeatherDataConverter.fromNWWeatherDataToForecastWeatherData(extendedWeather, weatherParams))
                .toFlowable();
    }

    @Override
    public void saveWeather(FullWeatherModel fullWeatherModel) {
        databaseRepo.saveWeather(fullWeatherModel);
    }
}
