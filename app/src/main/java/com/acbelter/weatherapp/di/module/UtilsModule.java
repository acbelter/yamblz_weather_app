package com.acbelter.weatherapp.di.module;

import android.content.Context;
import android.os.Build;

import com.acbelter.weatherapp.scheduler.WeatherScheduleJob;

import java.util.Locale;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    @Singleton
    @Provides
    public Locale provideLocale(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.getResources().getConfiguration().getLocales().get(0);
        } else {
            return context.getResources().getConfiguration().locale;
        }
    }

    @Provides
    @Singleton
    public WeatherScheduleJob provideWeatherScheduleJob() {
        return new WeatherScheduleJob();
    }
}
