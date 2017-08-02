package com.acbelter.weatherapp.domain.repository;

import com.acbelter.weatherapp.domain.model.settings.SettingsData;

import io.reactivex.Observable;

public interface SettingsRepo {

    Observable<SettingsData> getUserSettings();
}
