package com.acbelter.weatherapp.di.module;

import com.acbelter.weatherapp.data.database.DatabaseService;
import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.data.repository.WeatherRepoImpl;
import com.acbelter.weatherapp.di.scope.WeatherScope;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class WeatherModule {
    @Provides
    @WeatherScope
    WeatherRepo provideWeatherRepo(DatabaseService databaseService,
                                   NetworkService networkService) {
        return new WeatherRepoImpl(databaseService, networkService);
    }

    @Provides
    @WeatherScope
    WeatherInteractor provideWeatherInteractor(WeatherRepo weatherRepo) {
        return new WeatherInteractor(weatherRepo);
    }
}
