package com.acbelter.weatherapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

public class PreferencesStorage {
    public static final String KEY_PREF_UPDATE_INTERVAL = "pref_update_interval";

    private SharedPreferences mPrefs;

    public PreferencesStorage(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public int getPrefUpdateInterval() {
        return mPrefs.getInt(KEY_PREF_UPDATE_INTERVAL, 1);
    }

    public void setKeyPrefUpdateInterval(int value) {
        mPrefs.edit().putInt(KEY_PREF_UPDATE_INTERVAL, value).apply();
    }
}
