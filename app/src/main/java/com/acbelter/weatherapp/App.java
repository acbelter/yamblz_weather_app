package com.acbelter.weatherapp;

import android.app.Application;

import com.acbelter.weatherapp.di.component.ActivityComponent;
import com.acbelter.weatherapp.di.component.AppComponent;
import com.acbelter.weatherapp.di.component.DaggerAppComponent;
import com.acbelter.weatherapp.di.module.ActivityModule;
import com.acbelter.weatherapp.di.module.AppModule;
import com.acbelter.weatherapp.scheduler.WeatherScheduleJob;

import timber.log.Timber;

public class App extends Application {
    private static App instance;

    private AppComponent appComponent;
    private ActivityComponent activityComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());

        setInstance(this);
        this.appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        startJob();
    }

    private void startJob() {
        WeatherScheduleJob scheduleJob = new WeatherScheduleJob();
        scheduleJob.startJob();
    }

    private static void setInstance(App instance) {
        App.instance = instance;
    }

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public ActivityComponent plusActivityComponent() {
        if (activityComponent == null) {
            activityComponent = appComponent.addWeatherComponent(new ActivityModule());
        }

        return activityComponent;
    }

    public void releaseActivityComponent() {
        activityComponent = null;
    }
}
