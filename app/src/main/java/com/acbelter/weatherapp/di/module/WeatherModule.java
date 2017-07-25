package com.acbelter.weatherapp.di.module;

import com.acbelter.weatherapp.data.database.DatabaseService;
import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.data.repository.PreferencesRepo;
import com.acbelter.weatherapp.data.repository.city.CityRepoImpl;
import com.acbelter.weatherapp.data.repository.weather.WeatherRepoImpl;
import com.acbelter.weatherapp.di.scope.WeatherScope;
import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.repository.CityRepo;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class WeatherModule {
    @Provides
    @WeatherScope
    WeatherRepo provideWeatherRepo(DatabaseService databaseService,
                                   NetworkService networkService, PreferencesRepo preferencesRepo) {
        return new WeatherRepoImpl(databaseService, networkService, preferencesRepo);
    }

    @Provides
    @WeatherScope
    CityRepo provideCityRepo(NetworkService networkService, PreferencesRepo preferencesRepo) {
        return new CityRepoImpl(networkService, preferencesRepo);
    }

    @Provides
    @WeatherScope
    WeatherInteractor provideWeatherInteractor(WeatherRepo weatherRepo) {
        return new WeatherInteractor(weatherRepo);
    }

    @Provides
    @WeatherScope
    CityInteractor provideCityInteractor(CityRepo cityRepo) {
        return new CityInteractor(cityRepo);
    }
}
