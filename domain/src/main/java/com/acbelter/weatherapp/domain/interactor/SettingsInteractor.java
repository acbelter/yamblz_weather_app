package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.settings.SettingsData;
import com.acbelter.weatherapp.domain.repository.SettingsRepo;

import io.reactivex.Observable;

public class SettingsInteractor {

    private SettingsRepo settingsRepo;

    public SettingsInteractor(SettingsRepo settingsRepo) {
        this.settingsRepo = settingsRepo;
    }

    public Observable<SettingsData> getUserSettings() {
        return settingsRepo.getUserSettings();
    }
}
