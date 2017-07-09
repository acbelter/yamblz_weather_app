package com.acbelter.weatherapp;

import android.app.Application;

import com.acbelter.weatherapp.di.ComponentManager;

import timber.log.Timber;

public class App extends Application {
    private static ComponentManager sComponentManager;

    @Override
    public void onCreate() {
        super.onCreate();
        sComponentManager = new ComponentManager(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static ComponentManager getComponentManager() {
        return sComponentManager;
    }
}
