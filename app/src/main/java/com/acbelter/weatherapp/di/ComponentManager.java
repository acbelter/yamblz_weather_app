package com.acbelter.weatherapp.di;

import android.content.Context;

import com.acbelter.weatherapp.di.component.AppComponent;
import com.acbelter.weatherapp.di.component.DaggerAppComponent;
import com.acbelter.weatherapp.di.component.WeatherComponent;
import com.acbelter.weatherapp.di.module.AppModule;
import com.acbelter.weatherapp.di.module.DatabaseModule;
import com.acbelter.weatherapp.di.module.NetworkModule;
import com.acbelter.weatherapp.di.module.PreferencesModule;
import com.acbelter.weatherapp.di.module.WeatherModule;

public class ComponentManager {
    private AppComponent mAppComponent;
    private WeatherComponent mWeatherComponent;

    public ComponentManager(Context context) {
        mAppComponent = buildAppComponent(context.getApplicationContext());
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public WeatherComponent addWeatherComponent() {
        if (mWeatherComponent == null) {
            mWeatherComponent = mAppComponent.addWeatherComponent(new WeatherModule());
        }
        return mWeatherComponent;
    }

    public void removeWeatherComponent() {
        mWeatherComponent = null;
    }

    private AppComponent buildAppComponent(Context context) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(context))
                .databaseModule(new DatabaseModule())
                .networkModule(new NetworkModule())
                .preferencesModule(new PreferencesModule())
                .build();
    }
}
