package com.acbelter.weatherapp.domain.repository;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.settings.SettingsData;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

import io.reactivex.Observable;

public interface SettingsRepo {

    Observable<SettingsData> getUserSettings();

    void saveTemperatureMetric(TemperatureMetric metric);

    void saveUpdateInterval(long interval);

    void saveSelectedCity(CityData cityData);
}
