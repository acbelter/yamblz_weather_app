package com.acbelter.weatherapp.domain.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.acbelter.weatherapp.domain.model.settings.SettingsData;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

import io.reactivex.Observable;

public interface SettingsRepo {

    @MainThread
    Observable<SettingsData> getUserSettings();

    void saveTemperatureMetric(@NonNull TemperatureMetric metric);

    void saveUpdateInterval(@NonNull long interval);
}
