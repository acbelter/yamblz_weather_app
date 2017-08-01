package com.acbelter.weatherapp.data.repository.weather;

import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.data.repository.preference.PreferencesRepo;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import io.reactivex.Observable;
import timber.log.Timber;

public class WeatherRepoImpl implements WeatherRepo {

    private NetworkService mNetworkService;
    private PreferencesRepo mPreferencesRepo;

    public WeatherRepoImpl(NetworkService networkService, PreferencesRepo preferencesRepo) {
        mNetworkService = networkService;
        this.mPreferencesRepo = preferencesRepo;
    }

    @Override
    public Observable<WeatherData> getCurrentWeather(WeatherParams params) {
        return mNetworkService.getCurrentWeather(params)
                .map(WeatherDataConverter::fromNetworkData)
                .doOnNext(data -> {
                    Timber.d("Current weather data from network: %s", data);
                });
    }

    @Override
    public void saveWeather(WeatherData weatherData) {
        mPreferencesRepo.setLastWeatherData(weatherData);
        long updateTimestamp = System.currentTimeMillis();
        mPreferencesRepo.setLastUpdateTimestamp(updateTimestamp);
    }
}
