package com.acbelter.weatherapp.data.repository.preference;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.settings.SettingsData;
import com.acbelter.weatherapp.domain.repository.SettingsRepo;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

import io.reactivex.Observable;

public class SettingsRepoImpl implements SettingsRepo {

    private final SettingsPreference preference;

    public SettingsRepoImpl(SettingsPreference preference) {
        this.preference = preference;
    }

    @Override
    public Observable<SettingsData> getUserSettings() {
        return preference.loadUserSettings();
    }

    @Override
    public void saveTemperatureMetric(TemperatureMetric metric) {
        preference.saveTemperatureMetric(metric);
    }

    @Override
    public void saveUpdateInterval(long interval) {
        preference.saveUpdateInterval(interval);
    }

    @Override
    public void saveSelectedCity(CityData cityData) {
        preference.saveCurrentCity(cityData);
    }
}
