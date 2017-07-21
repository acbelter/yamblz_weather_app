package com.acbelter.weatherapp.data.repository;

import com.acbelter.weatherapp.data.database.DatabaseService;
import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.domain.model.WeatherData;
import com.acbelter.weatherapp.domain.model.WeatherParams;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import io.reactivex.Observable;
import timber.log.Timber;

public class WeatherRepoImpl implements WeatherRepo {
    private DatabaseService mDatabaseService;
    private NetworkService mNetworkService;

    public WeatherRepoImpl(DatabaseService databaseService,
                           NetworkService networkService) {
        mDatabaseService = databaseService;
        mNetworkService = networkService;
    }

    @Override
    public Observable<WeatherData> getCurrentWeather(WeatherParams params) {
        return mNetworkService.getCurrentWeather(params)
                .map(WeatherDataConverter::fromNetworkData)
                .doOnNext(data -> {
                    Timber.d("Current weather data from network: %s", data);
                });
    }
}
