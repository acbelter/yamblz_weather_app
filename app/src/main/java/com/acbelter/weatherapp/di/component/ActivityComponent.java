package com.acbelter.weatherapp.di.component;

import com.acbelter.weatherapp.di.module.ActivityModule;
import com.acbelter.weatherapp.di.scope.ActivityScope;
import com.acbelter.weatherapp.mvp.view.about.InfoFragment;
import com.acbelter.weatherapp.mvp.view.activity.MainActivity;
import com.acbelter.weatherapp.mvp.view.search.SearchFragment;
import com.acbelter.weatherapp.mvp.view.settings.SettingsFragment;
import com.acbelter.weatherapp.mvp.view.weather.WeatherFragment;
import com.acbelter.weatherapp.scheduler.WeatherScheduleJob;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(WeatherFragment weatherFragment);

    void inject(InfoFragment infoFragment);

    void inject(SettingsFragment settingsFragment);

    void inject(SearchFragment searchFragment);

    void inject(WeatherScheduleJob scheduleJob);
}
