package com.acbelter.weatherapp.data.repository.weather;

import com.acbelter.weatherapp.data.database.DatabaseService;
import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.data.repository.PreferencesRepo;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import io.reactivex.Observable;
import timber.log.Timber;

public class WeatherRepoImpl implements WeatherRepo {

    private DatabaseService mDatabaseService;
    private NetworkService mNetworkService;
    private PreferencesRepo mPreferencesRepo;

    public WeatherRepoImpl(DatabaseService databaseService,
                           NetworkService networkService, PreferencesRepo preferencesRepo) {
        mDatabaseService = databaseService;
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
