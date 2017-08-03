package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.settings.SettingsData;
import com.acbelter.weatherapp.domain.repository.SettingsRepo;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

import io.reactivex.Observable;

public class SettingsInteractor {

    private SettingsRepo settingsRepo;

    public SettingsInteractor(SettingsRepo settingsRepo) {
        this.settingsRepo = settingsRepo;
    }

    public Observable<SettingsData> getUserSettings() {
        return settingsRepo.getUserSettings();
    }

    public void saveTemperatureMetric(TemperatureMetric metric) {
        settingsRepo.saveTemperatureMetric(metric);
    }

    public void saveUpdateInterval(long interval) {
        settingsRepo.saveUpdateInterval(interval);
    }

    public void saveSelectedCity(CityData cityData) {
        settingsRepo.saveSelectedCity(cityData);
    }
}
