package com.acbelter.weatherapp.di.component;

import com.acbelter.weatherapp.di.module.WeatherModule;
import com.acbelter.weatherapp.di.scope.WeatherScope;
import com.acbelter.weatherapp.ui.weather.WeatherFragment;

import dagger.Subcomponent;

@WeatherScope
@Subcomponent(modules = {WeatherModule.class})
public interface WeatherComponent {
    void inject(WeatherFragment weatherFragment);
}
