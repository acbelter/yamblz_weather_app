package com.acbelter.weatherapp.domain.repository;

import android.support.annotation.NonNull;

import com.acbelter.weatherapp.domain.model.settings.SettingsData;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

public interface SettingsRepo {

    SettingsData getUserSettings();

    void saveTemperatureMetric(@NonNull TemperatureMetric metric);

    void saveUpdateInterval(@NonNull long interval);
}
