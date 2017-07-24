package com.acbelter.weatherapp.presentation;

import android.content.Context;

import com.acbelter.weatherapp.WeatherUpdateScheduler;
import com.acbelter.weatherapp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.ui.settings.SettingsView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import timber.log.Timber;

@InjectViewState
public class SettingsPresenter extends BasePresenter<SettingsView> {
    @Inject
    public SettingsPresenter() {
    }

    public void restartWeatherUpdating(Context context, int newUpdateInterval) {
        Timber.d("Restart weather updating");
        // Clear last weather data and restart update process
        if (newUpdateInterval > 0) {
            WeatherUpdateScheduler.startWeatherUpdates(context, newUpdateInterval, true);
        } else {
            WeatherUpdateScheduler.stopWeatherUpdates(context);
        }
    }
}
