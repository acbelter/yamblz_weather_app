package com.acbelter.weatherapp.ui.settings;

import android.os.Bundle;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.PreferencesStorage;
import com.acbelter.weatherapp.R;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;

import javax.inject.Inject;

import timber.log.Timber;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Inject
    PreferencesStorage mPrefsStorage;

    @Override
    public void onCreatePreferencesFix(Bundle savedInstanceState, String rootKey) {
        App.getComponentManager().getAppComponent().inject(this);
        setPreferencesFromResource(R.xml.preferences, rootKey);
        Timber.d("Update weather interval: " + mPrefsStorage.getPrefUpdateInterval());
    }

    public static String tag() {
        return SettingsFragment.class.getSimpleName();
    }
}
