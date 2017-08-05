package com.acbelter.weatherapp.data.repository.weather;

import com.acbelter.weatherapp.data.network.NetworkRepo;
import com.acbelter.weatherapp.data.repository.preference.SettingsPreference;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.repository.DatabaseRepo;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

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
    public Flowable<CurrentWeatherData> getCurrentWeather() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return databaseRepo.getCurrentWeather(weatherParams)
                .toFlowable()
                .onErrorResumeNext(networkRepo.getCurrentWeather(weatherParams)
                        .map(currentWeather -> WeatherDataConverter.fromNWWeatherDataToCurrentWeatherData(currentWeather, weatherParams)))
                .doOnNext(data -> {
                    Timber.d("Current weather data from network: %s", data);
                });
    }

    @Override
    public Flowable<WeatherForecast> getForecast() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return databaseRepo.getForecastWeather(weatherParams)
                .toFlowable()
                .onErrorResumeNext(networkRepo.getForecastWeather(weatherParams)
                        .map(forecastWeather -> WeatherDataConverter.fromNWWeatherDataToForecastWeatherData(forecastWeather, weatherParams)))
                .doOnNext(data -> {
                    Timber.d("Current weather data from network: %s", data);
                });
    }

    @Override
    public Flowable<CurrentWeatherData> updateCurrentWeather() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return networkRepo.getCurrentWeather(weatherParams)
                .map(currentWeather -> WeatherDataConverter.fromNWWeatherDataToCurrentWeatherData(currentWeather, weatherParams));
    }

    @Override
    public Flowable<WeatherForecast> updateForecast() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return networkRepo.getForecastWeather(weatherParams)
                .map(extendedWeather -> WeatherDataConverter.fromNWWeatherDataToForecastWeatherData(extendedWeather, weatherParams));
    }

    @Override
    public void saveWeather(FullWeatherModel fullWeatherModel) {
        databaseRepo.saveWeather(fullWeatherModel);
    }
}
