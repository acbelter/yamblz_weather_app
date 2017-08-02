package com.acbelter.weatherapp.di.component;

import com.acbelter.weatherapp.di.module.ActivityModule;
import com.acbelter.weatherapp.di.scope.ActivityScope;
import com.acbelter.weatherapp.scheduler.WeatherScheduleJob;
import com.acbelter.weatherapp.ui.search.SearchActivity;
import com.acbelter.weatherapp.ui.settings.SettingsActivity;
import com.acbelter.weatherapp.ui.settings.SettingsFragment;
import com.acbelter.weatherapp.ui.weather.WeatherFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(WeatherFragment weatherFragment);

    void inject(SettingsActivity settingsActivity);

    void inject(SettingsFragment settingsFragment);

    void inject(SearchActivity searchActivity);

    void inject(WeatherScheduleJob scheduleJob);
}
