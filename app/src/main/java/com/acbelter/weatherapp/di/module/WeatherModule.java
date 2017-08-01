package com.acbelter.weatherapp.di.module;

import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.data.repository.preference.PreferencesRepo;
import com.acbelter.weatherapp.data.repository.weather.WeatherRepoImpl;
import com.acbelter.weatherapp.di.scope.ActivityScope;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class WeatherModule {
    @Provides
    @ActivityScope
    WeatherRepo provideWeatherRepo(NetworkService networkService, PreferencesRepo preferencesRepo) {
        return new WeatherRepoImpl(networkService, preferencesRepo);
    }

    @Provides
    @ActivityScope
    WeatherInteractor provideWeatherInteractor(WeatherRepo weatherRepo) {
        return new WeatherInteractor(weatherRepo, Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
