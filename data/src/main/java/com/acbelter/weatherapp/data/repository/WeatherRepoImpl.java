package com.acbelter.weatherapp.data.repository;

import com.acbelter.weatherapp.data.database.DatabaseService;
import com.acbelter.weatherapp.data.dbmodel.DatabaseWeatherData;
import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.domain.model.WeatherData;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
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
    public Observable<List<WeatherData>> getWeather() {
        Observable<List<DatabaseWeatherData>> databaseObservable = mDatabaseService.getAllWeatherData()
                .doOnNext(weatherDataList ->
                        Timber.d("Loaded weather data from database: " + weatherDataList.size()));

        Observable<List<DatabaseWeatherData>> networkObservable = mNetworkService.getWeather()
                .flatMapIterable(list -> list)
                .map(WeatherDataConverter::fromNetworkToDatabaseData)
                .toList()
                .toObservable()
                .doOnNext(weatherDataList -> {
                    Timber.d("Start saving weather data to database: " + weatherDataList.size());
                    mDatabaseService.insertAllWeatherData(weatherDataList)
                            .doOnNext(count -> {
                                Timber.d("Finish saving weather data to database: " + count);
                            })
                            .subscribeOn(Schedulers.io())
                            .subscribe();
                });

        return Observable
                .concat(databaseObservable, networkObservable)
                .filter(weatherDataList -> {
                    Timber.d("Filter weather data: " + weatherDataList.size());
                    return !weatherDataList.isEmpty();
                })
                .firstOrError()
                .toObservable()
                .flatMapIterable(list -> list)
                .map(WeatherDataConverter::fromDatabaseData)
                .toList()
                .toObservable();
    }
}
