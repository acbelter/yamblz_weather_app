package com.acbelter.weatherapp.data.repository.preference;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.settings.SettingsData;
import com.acbelter.weatherapp.domain.repository.SettingsRepo;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

import io.reactivex.Observable;

public class SettingsRepoImpl implements SettingsRepo {

    @NonNull
    private final SettingsPreference preference;

    public SettingsRepoImpl(@NonNull SettingsPreference preference) {
        this.preference = preference;
    }

    @Override
    @MainThread
    public Observable<SettingsData> getUserSettings() {
        return preference.loadUserSettings();
    }

    @Override
    public void saveTemperatureMetric(@NonNull TemperatureMetric metric) {
        preference.saveTemperatureMetric(metric);
    }

    @Override
    public void saveUpdateInterval(@NonNull long interval) {
        preference.saveUpdateInterval(interval);
    }
}
