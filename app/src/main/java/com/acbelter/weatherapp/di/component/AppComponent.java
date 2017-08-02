package com.acbelter.weatherapp.di.component;

import com.acbelter.weatherapp.di.module.ActivityModule;
import com.acbelter.weatherapp.di.module.AppModule;
import com.acbelter.weatherapp.di.module.DataModule;
import com.acbelter.weatherapp.di.module.NetworkModule;
import com.acbelter.weatherapp.di.module.UtilsModule;
import com.acbelter.weatherapp.mvp.presentation.SettingsPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        DataModule.class,
        UtilsModule.class})
public interface AppComponent {
    ActivityComponent addWeatherComponent(ActivityModule activityModule);

    void inject(SettingsPresenter presenter);
}