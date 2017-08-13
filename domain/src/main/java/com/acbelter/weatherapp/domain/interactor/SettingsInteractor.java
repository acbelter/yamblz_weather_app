package com.acbelter.weatherapp.domain.interactor;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.acbelter.weatherapp.domain.model.settings.SettingsData;
import com.acbelter.weatherapp.domain.repository.SettingsRepo;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

public class SettingsInteractor {

    @NonNull
    private final SettingsRepo settingsRepo;

    public SettingsInteractor(@NonNull SettingsRepo settingsRepo) {
        this.settingsRepo = settingsRepo;
    }

    @MainThread
    public SettingsData getUserSettings() {
        return settingsRepo.getUserSettings();
    }

    public void saveTemperatureMetric(@NonNull TemperatureMetric metric) {
        settingsRepo.saveTemperatureMetric(metric);
    }

    public void saveUpdateInterval(@NonNull long interval) {
        settingsRepo.saveUpdateInterval(interval);
    }
}
