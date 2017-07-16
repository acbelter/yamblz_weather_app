package com.acbelter.weatherapp.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.PreferencesStorage;
import com.acbelter.weatherapp.R;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;

import javax.inject.Inject;

import timber.log.Timber;

public class SettingsFragment extends PreferenceFragmentCompat implements
        SharedPreferences.OnSharedPreferenceChangeListener {
    @Inject
    PreferencesStorage mPrefsStorage;
    private SettingsListener mListener;

    public interface SettingsListener {
        void onUpdateIntervalChanged(int newUpdateInterval);
    }

    @Override
    public void onCreatePreferencesFix(Bundle savedInstanceState, String rootKey) {
        App.getComponentManager().getAppComponent().inject(this);
        addPreferencesFromResource(R.xml.preferences);
        Timber.d("Stored weather update interval: " + mPrefsStorage.getUpdateInterval());
        mPrefsStorage.addListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPrefsStorage.removeListener(this);
        App.getComponentManager().removeWeatherComponent();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (SettingsListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Parent activity must implement SettingsListener");
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (PreferencesStorage.KEY_UPDATE_INTERVAL.equals(key)) {
            mListener.onUpdateIntervalChanged(mPrefsStorage.getUpdateInterval());
        }
    }

    public static String tag() {
        return SettingsFragment.class.getSimpleName();
    }
}
