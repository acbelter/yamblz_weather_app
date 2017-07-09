package com.acbelter.weatherapp.di.component;

import com.acbelter.weatherapp.di.module.AppModule;
import com.acbelter.weatherapp.di.module.DatabaseModule;
import com.acbelter.weatherapp.di.module.NetworkModule;
import com.acbelter.weatherapp.di.module.WeatherModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        DatabaseModule.class})
public interface AppComponent {
    WeatherComponent addWeatherComponent(WeatherModule weatherModule);
}