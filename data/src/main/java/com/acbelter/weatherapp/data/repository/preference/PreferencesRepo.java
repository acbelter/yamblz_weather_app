package com.acbelter.weatherapp.data.repository.preference;

import android.content.SharedPreferences;

import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.google.gson.Gson;

import io.reactivex.annotations.Nullable;

public class PreferencesRepo {
    public static final String KEY_CURRENT_CITY = "pref_current_city";
    public static final String KEY_UPDATE_INTERVAL = "pref_update_interval";
    public static final String KEY_LAST_UPDATE_TIMESTAMP = "pref_last_update_timestamp";
    public static final String KEY_LAST_WEATHER_DATA = "pref_last_weather_data";

    private SharedPreferences mPrefs;

    public PreferencesRepo(SharedPreferences sharedPreferences) {
        mPrefs = sharedPreferences;
    }

    public void addListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        if (listener != null) {
            mPrefs.registerOnSharedPreferenceChangeListener(listener);
        }
    }

    public void removeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        if (listener != null) {
            mPrefs.unregisterOnSharedPreferenceChangeListener(listener);
        }
    }

    public void setCurrentCity(String city) {
        mPrefs.edit().putString(KEY_CURRENT_CITY, city).apply();
    }

    public String getCurrentCity() {
        // FIXME Don't use Moscow as default city
        return mPrefs.getString(KEY_CURRENT_CITY, "Moscow, TN 38057");
    }

    public void setUpdateInterval(int interval) {
        mPrefs.edit().putInt(KEY_UPDATE_INTERVAL, interval).apply();
    }

    public int getUpdateInterval() {
        return mPrefs.getInt(KEY_UPDATE_INTERVAL, 1);
    }

    public void setLastUpdateTimestamp(long timestamp) {
        mPrefs.edit().putLong(KEY_LAST_UPDATE_TIMESTAMP, timestamp).apply();
    }

    public long getLastUpdateTimestamp() {
        return mPrefs.getLong(KEY_LAST_UPDATE_TIMESTAMP, 0L);
    }

    public void setLastWeatherData(WeatherData weatherData) {
        if (weatherData == null) {
            mPrefs.edit().remove(KEY_LAST_WEATHER_DATA).apply();
            return;
        }

        String weatherDataJson = new Gson().toJson(weatherData);
        mPrefs.edit().putString(KEY_LAST_WEATHER_DATA, weatherDataJson).apply();
    }

    public
    @Nullable
    WeatherData getLastWeatherData() {
        if (!mPrefs.contains(KEY_LAST_WEATHER_DATA)) {
            return null;
        }

        String weatherDataJson = mPrefs.getString(KEY_LAST_WEATHER_DATA, null);
        return new Gson().fromJson(weatherDataJson, WeatherData.class);
    }
}
