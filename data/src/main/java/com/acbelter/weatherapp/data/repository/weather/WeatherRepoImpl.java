package com.acbelter.weatherapp.data.repository.weather;

import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.data.repository.preference.SettingsPreference;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import io.reactivex.Observable;
import timber.log.Timber;

public class WeatherRepoImpl implements WeatherRepo {

    private NetworkService networkService;
    private SettingsPreference settingsPreference;

    public WeatherRepoImpl(NetworkService networkService, SettingsPreference settingsPreference) {
        this.networkService = networkService;
        this.settingsPreference = settingsPreference;
    }

    @Override
    public Observable<WeatherData> getCurrentWeather() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return settingsPreference.getLastWeatherData()
                .onErrorResumeNext(networkService.getCurrentWeather(weatherParams)
                        .map(currentWeather -> WeatherDataConverter.currentWeatherFromNetworkData(currentWeather, weatherParams)))
                .doOnNext(data -> {
                    Timber.d("Current weather data from network: %s", data);
                });
    }

    @Override
    public Observable<WeatherForecast> getForecast() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return networkService.getForecast(weatherParams)
                .map(extendedWeather -> WeatherDataConverter.forecastFromNetworkData(extendedWeather, weatherParams));
    }

    @Override
    public Observable<WeatherData> updateCurrentWeather() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return networkService.getCurrentWeather(weatherParams)
                .map(currentWeather -> WeatherDataConverter.currentWeatherFromNetworkData(currentWeather, weatherParams));
    }

    @Override
    public Observable<WeatherForecast> updateForecast() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return networkService.getForecast(weatherParams)
                .map(extendedWeather -> WeatherDataConverter.forecastFromNetworkData(extendedWeather, weatherParams));
    }

    @Override
    public void saveWeather(WeatherData weatherData) {
        settingsPreference.setLastWeatherData(weatherData);
        long updateTimestamp = System.currentTimeMillis();
        settingsPreference.setLastUpdateTimestamp(updateTimestamp);
    }
}
