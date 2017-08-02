package com.acbelter.weatherapp.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.data.repository.preference.SettingsPreference;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;

import javax.inject.Inject;

import timber.log.Timber;

public class SettingsFragment extends PreferenceFragmentCompat implements
        SharedPreferences.OnSharedPreferenceChangeListener {
    @Inject
    SettingsPreference mPrefsRepo;
    private SettingsListener mListener;

    public interface SettingsListener {
        void onUpdateIntervalChanged(long newUpdateInterval);
    }

    @Override
    public void onCreatePreferencesFix(Bundle savedInstanceState, String rootKey) {
        App.getInstance().plusActivityComponent().inject(this);
        addPreferencesFromResource(R.xml.preferences);
        Timber.d("Stored weather update interval: %s", mPrefsRepo.loadUpdateInterval());
        mPrefsRepo.addListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPrefsRepo.removeListener(this);
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
        if (SettingsPreference.KEY_UPDATE_INTERVAL.equals(key)) {
            mListener.onUpdateIntervalChanged(mPrefsRepo.loadUpdateInterval());
        }
    }

    public static String tag() {
        return SettingsFragment.class.getSimpleName();
    }
}
