package com.acbelter.weatherapp.mvp.presentation;

import android.support.annotation.NonNull;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.domain.interactor.SettingsInteractor;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;
import com.acbelter.weatherapp.mvp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.mvp.view.settings.SettingsView;
import com.acbelter.weatherapp.scheduler.WeatherScheduleJob;

import javax.inject.Inject;

public class SettingsPresenter extends BasePresenter<SettingsView> {

    @NonNull
    private final SettingsInteractor settingsInteractor;

    @Inject
    WeatherScheduleJob scheduleJob;

    @Inject
    public SettingsPresenter(@NonNull SettingsInteractor settingsInteractor) {
        this.settingsInteractor = settingsInteractor;

        App.getInstance().getAppComponent().inject(this);
    }

    public void showSettings() {
        if (getView() == null)
            return;
        getView().setSettings(settingsInteractor.getUserSettings());
    }

    public void saveTemperatureMetric(@NonNull TemperatureMetric metric) {
        settingsInteractor.saveTemperatureMetric(metric);
    }

    public void saveUpdateInterval(@NonNull long interval) {
        settingsInteractor.saveUpdateInterval(interval);

        scheduleJob.startJob();
    }
}
