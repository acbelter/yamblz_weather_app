package com.acbelter.weatherapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.acbelter.weatherapp.data.repository.preference.PreferencesRepo;

import javax.inject.Inject;

import timber.log.Timber;

public class WeatherUpdateReceiver extends BroadcastReceiver {
    @Inject
    PreferencesRepo mPrefsRepo;

    @Override
    public void onReceive(Context context, Intent intent) {
        App.getComponentManager().getAppComponent().inject(this);
        Timber.d("Update weather event: %s", System.currentTimeMillis());

        String action = intent.getAction();
        Timber.d("Received broadcast with action: %s", action);
        if ("android.intent.action.BOOT_COMPLETED".equals(action) ||
                "android.intent.action.ACTION_MY_PACKAGE_REPLACED".equals(action)) {
            WeatherUpdateScheduler.startWeatherUpdates(context, mPrefsRepo.getUpdateInterval(), false);
        } else {
            context.startService(new Intent(context, WeatherUpdateService.class));
        }
    }
}
