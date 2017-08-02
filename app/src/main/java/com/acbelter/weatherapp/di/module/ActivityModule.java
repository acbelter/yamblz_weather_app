package com.acbelter.weatherapp.di.module;

import com.acbelter.weatherapp.di.scope.ActivityScope;
import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.interactor.SettingsInteractor;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.repository.CityRepo;
import com.acbelter.weatherapp.domain.repository.SettingsRepo;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class ActivityModule {

    @Provides
    @ActivityScope
    WeatherInteractor provideWeatherInteractor(WeatherRepo weatherRepo) {
        return new WeatherInteractor(weatherRepo, Schedulers.io(), AndroidSchedulers.mainThread());
    }

    @Provides
    @ActivityScope
    CityInteractor provideCityInteractor(CityRepo cityRepo) {
        return new CityInteractor(cityRepo, Schedulers.io(), AndroidSchedulers.mainThread());
    }

    @Provides
    @ActivityScope
    SettingsInteractor provideSettingsInteractor(SettingsRepo settingsRepo) {
        return new SettingsInteractor(settingsRepo);

    }
}
