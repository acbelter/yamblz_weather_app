package com.acbelter.weatherapp.data.repository.preference;

import com.acbelter.weatherapp.domain.model.settings.SettingsData;
import com.acbelter.weatherapp.domain.repository.SettingsRepo;

import io.reactivex.Observable;

public class SettingsRepoImpl implements SettingsRepo {

    private SettingsPreference preference;

    public SettingsRepoImpl(SettingsPreference preference) {
        this.preference = preference;
    }

    @Override
    public Observable<SettingsData> getUserSettings() {
        return preference.loadUserSettings();
    }
}
