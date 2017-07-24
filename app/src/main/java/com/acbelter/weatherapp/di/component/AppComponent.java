package com.acbelter.weatherapp.di.component;

import com.acbelter.weatherapp.WeatherUpdateReceiver;
import com.acbelter.weatherapp.di.module.AppModule;
import com.acbelter.weatherapp.di.module.DatabaseModule;
import com.acbelter.weatherapp.di.module.NetworkModule;
import com.acbelter.weatherapp.di.module.PreferencesModule;
import com.acbelter.weatherapp.di.module.WeatherModule;
import com.acbelter.weatherapp.ui.search.SearchActivity;
import com.acbelter.weatherapp.ui.settings.SettingsActivity;
import com.acbelter.weatherapp.ui.settings.SettingsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        DatabaseModule.class,
        PreferencesModule.class})
public interface AppComponent {
    WeatherComponent addWeatherComponent(WeatherModule weatherModule);
    void inject(SettingsFragment settingsFragment);
    void inject(SettingsActivity settingsActivity);
    void inject(WeatherUpdateReceiver updateReceiver);
}