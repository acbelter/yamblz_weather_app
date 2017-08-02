package com.acbelter.weatherapp.di.module;

import com.acbelter.weatherapp.scheduler.WeatherScheduleJob;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    @Provides
    @Singleton
    public WeatherScheduleJob provideWeatherScheduleJob() {
        return new WeatherScheduleJob();
    }
}
