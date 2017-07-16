package com.acbelter.weatherapp.presentation;

import android.content.Context;

import com.acbelter.weatherapp.PreferencesStorage;
import com.acbelter.weatherapp.WeatherUpdateScheduler;
import com.acbelter.weatherapp.ui.settings.SettingsView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import timber.log.Timber;

@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView> {
    private PreferencesStorage mPrefsStorage;

    @Inject
    public SettingsPresenter(PreferencesStorage prefsStorage) {
        mPrefsStorage = prefsStorage;
    }

    public void restartWeatherUpdating(Context context, int newUpdateInterval) {
        Timber.d("Restart weather updating");
        // Clear last weather data and restart update process
        if (newUpdateInterval > 0) {
            mPrefsStorage.setLastWeatherData(null);
            mPrefsStorage.setLastUpdateTimestamp(0L);
            WeatherUpdateScheduler.restartWeatherUpdates(context, newUpdateInterval);
        } else {
            WeatherUpdateScheduler.stopWeatherUpdates(context);
        }
    }
}
