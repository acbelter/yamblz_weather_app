package com.acbelter.weatherapp.di;

import android.content.Context;

import com.acbelter.weatherapp.di.component.ActivityComponent;
import com.acbelter.weatherapp.di.component.AppComponent;
import com.acbelter.weatherapp.di.component.DaggerAppComponent;
import com.acbelter.weatherapp.di.module.AppModule;
import com.acbelter.weatherapp.di.module.DatabaseModule;
import com.acbelter.weatherapp.di.module.NetworkModule;
import com.acbelter.weatherapp.di.module.PreferencesModule;
import com.acbelter.weatherapp.di.module.WeatherModule;

public class ComponentManager {
    private AppComponent mAppComponent;
    private ActivityComponent mActivityComponent;

    public ComponentManager(Context context) {
        mAppComponent = buildAppComponent(context.getApplicationContext());
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public ActivityComponent addWeatherComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = mAppComponent.addWeatherComponent(new WeatherModule());
        }
        return mActivityComponent;
    }

    public void removeWeatherComponent() {
        mActivityComponent = null;
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
