package com.acbelter.weatherapp.data.repository.weather;

import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.data.repository.preference.PreferencesRepo;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import io.reactivex.Observable;
import timber.log.Timber;

public class WeatherRepoImpl implements WeatherRepo {

    private NetworkService networkService;
    private PreferencesRepo preferencesRepo;

    public WeatherRepoImpl(NetworkService networkService, PreferencesRepo preferencesRepo) {
        this.networkService = networkService;
        this.preferencesRepo = preferencesRepo;
    }

    @Override
    public Observable<WeatherData> getCurrentWeather() {
        WeatherParams weatherParams = new WeatherParams(preferencesRepo.getCurrentCity());
        return preferencesRepo.getLastWeatherData()
                .onErrorResumeNext(networkService.getCurrentWeather(weatherParams)
                        .map(WeatherDataConverter::fromNetworkData))
                .doOnNext(data -> {
                    Timber.d("Current weather data from network: %s", data);
                });
    }

    @Override
    public Observable<WeatherData> updateCurrentWeather() {
        WeatherParams weatherParams = new WeatherParams(preferencesRepo.getCurrentCity());
        return networkService.getCurrentWeather(weatherParams).map(WeatherDataConverter::fromNetworkData);
    }

    @Override
    public void saveWeather(WeatherData weatherData) {
        preferencesRepo.setLastWeatherData(weatherData);
        long updateTimestamp = System.currentTimeMillis();
        preferencesRepo.setLastUpdateTimestamp(updateTimestamp);
    }
}
