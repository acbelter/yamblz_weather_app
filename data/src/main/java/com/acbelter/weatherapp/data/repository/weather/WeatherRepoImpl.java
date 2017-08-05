package com.acbelter.weatherapp.data.repository.weather;

import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.data.repository.preference.SettingsPreference;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.repository.DatabaseRepo;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import io.reactivex.Flowable;
import timber.log.Timber;

public class WeatherRepoImpl implements WeatherRepo {

    private NetworkService networkService;
    private SettingsPreference settingsPreference;
    private DatabaseRepo databaseRepo;

    public WeatherRepoImpl(NetworkService networkService, SettingsPreference settingsPreference, DatabaseRepo databaseRepo) {
        this.networkService = networkService;
        this.settingsPreference = settingsPreference;
        this.databaseRepo = databaseRepo;
    }

    @Override
    public Flowable<CurrentWeatherData> getCurrentWeather() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return networkService.getCurrentWeather(weatherParams)
                .map(currentWeather -> WeatherDataConverter.currentWeatherFromNetworkData(currentWeather, weatherParams))
                .doOnNext(data -> {
                    Timber.d("Current weather data from network: %s", data);
                });
    }

    @Override
    public Flowable<WeatherForecast> getForecast() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return networkService.getForecast(weatherParams)
                .map(extendedWeather -> WeatherDataConverter.forecastFromNetworkData(extendedWeather, weatherParams));
    }

    @Override
    public Flowable<CurrentWeatherData> updateCurrentWeather() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return networkService.getCurrentWeather(weatherParams)
                .map(currentWeather -> WeatherDataConverter.currentWeatherFromNetworkData(currentWeather, weatherParams));
    }

    @Override
    public Flowable<WeatherForecast> updateForecast() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return networkService.getForecast(weatherParams)
                .map(extendedWeather -> WeatherDataConverter.forecastFromNetworkData(extendedWeather, weatherParams));
    }

    @Override
    public void saveWeather(CurrentWeatherData currentWeatherData) {
        settingsPreference.setLastWeatherData(currentWeatherData);
        long updateTimestamp = System.currentTimeMillis();
        settingsPreference.setLastUpdateTimestamp(updateTimestamp);
    }
}
