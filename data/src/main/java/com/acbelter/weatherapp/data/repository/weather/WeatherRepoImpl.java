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

import io.reactivex.Observable;
import io.reactivex.Single;
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
    public Single<CurrentWeatherFavorites> getCurrentWeather() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return databaseRepo.getCurrentWeather(weatherParams)
                .map(currentWeatherFavorites -> WeatherDataConverter.updateCurrentWeatherMetric(currentWeatherFavorites, weatherParams.getMetric()))
                .onErrorResumeNext(networkRepo.getCurrentWeather(weatherParams)
                        .map(currentWeather -> WeatherDataConverter
                                .fromNWWeatherDataToCurrentWeatherData(currentWeather, weatherParams)))
                .doOnSuccess(data -> {
                    Timber.d("Current weather data from network: %s", data);
                });
    }

    @Override
    public Single<List<ForecastWeatherFavorites>> getForecast() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return databaseRepo.getForecastWeather(weatherParams)
                .flatMap(forecastList -> Observable.fromIterable(forecastList)
                        .map(element -> WeatherDataConverter
                                .updateForecastWeatherMetric(element, weatherParams.getMetric()))
                        .toList())
                .onErrorResumeNext(networkRepo.getForecastWeather(weatherParams)
                        .map(forecastWeather -> WeatherDataConverter.fromNWWeatherDataToForecastWeatherData(forecastWeather, weatherParams)))
                .doOnSuccess(data -> {
                    Timber.d("Current weather data from network: %s", data);
                });
    }

    @Override
    public Single<CurrentWeatherFavorites> updateCurrentWeather() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return networkRepo.getCurrentWeather(weatherParams)
                .map(currentWeather -> WeatherDataConverter.fromNWWeatherDataToCurrentWeatherData(currentWeather, weatherParams));
    }

    @Override
    public Single<List<ForecastWeatherFavorites>> updateForecast() {
        WeatherParams weatherParams = new WeatherParams(settingsPreference.loadCurrentCity()
                , settingsPreference.loadTemperatureMetric());
        return networkRepo.getForecastWeather(weatherParams)
                .map(extendedWeather -> WeatherDataConverter
                        .fromNWWeatherDataToForecastWeatherData(extendedWeather, weatherParams));
    }

    @Override
    public void saveWeather(FullWeatherModel fullWeatherModel) {
        databaseRepo.saveWeather(fullWeatherModel);
    }
}
