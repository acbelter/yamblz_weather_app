package com.acbelter.weatherapp.di.component;

import com.acbelter.weatherapp.di.module.ActivityModule;
import com.acbelter.weatherapp.di.scope.ActivityScope;
import com.acbelter.weatherapp.mvp.view.search.SearchActivity;
import com.acbelter.weatherapp.mvp.view.settings.SettingsFragment;
import com.acbelter.weatherapp.mvp.view.weather.WeatherFragment;
import com.acbelter.weatherapp.scheduler.WeatherScheduleJob;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(WeatherFragment weatherFragment);

    void inject(SettingsFragment settingsFragment);

    void inject(SearchActivity searchActivity);

    void inject(WeatherScheduleJob scheduleJob);
}
