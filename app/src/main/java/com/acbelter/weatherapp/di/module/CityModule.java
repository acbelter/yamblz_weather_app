package com.acbelter.weatherapp.di.module;

import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.data.repository.PreferencesRepo;
import com.acbelter.weatherapp.data.repository.city.CityRepoImpl;
import com.acbelter.weatherapp.di.scope.ActivityScope;
import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.repository.CityRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class CityModule {

    @Provides
    @ActivityScope
    CityRepo provideCityRepo(NetworkService networkService, PreferencesRepo preferencesRepo) {
        return new CityRepoImpl(networkService, preferencesRepo);
    }

    @Provides
    @ActivityScope
    CityInteractor provideCityInteractor(CityRepo cityRepo) {
        return new CityInteractor(cityRepo);
    }
}
