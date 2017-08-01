package com.acbelter.weatherapp.di.component;

import com.acbelter.weatherapp.di.module.CityModule;
import com.acbelter.weatherapp.di.module.WeatherModule;
import com.acbelter.weatherapp.di.scope.ActivityScope;
import com.acbelter.weatherapp.ui.search.SearchActivity;
import com.acbelter.weatherapp.ui.weather.WeatherFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {WeatherModule.class, CityModule.class})
public interface ActivityComponent {
    void inject(WeatherFragment weatherFragment);

    void inject(SearchActivity searchActivity);
}
