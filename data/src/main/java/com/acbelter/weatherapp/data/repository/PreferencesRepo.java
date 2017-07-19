package com.acbelter.weatherapp.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.acbelter.weatherapp.domain.model.WeatherData;
import com.acbelter.weatherapp.domain.model.WeatherType;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreferencesRepo {
    public static final String KEY_UPDATE_INTERVAL = "pref_update_interval";
    public static final String KEY_LAST_UPDATE_TIMESTAMP = "pref_last_update_timestamp";
    public static final String KEY_LAST_WEATHER_DATA = "pref_last_weather_data";

    private SharedPreferences mPrefs;

    public PreferencesRepo(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
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

    public void setUpdateInterval(int value) {
        mPrefs.edit().putInt(KEY_UPDATE_INTERVAL, value).apply();
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

        String weatherDataJson = new Gson().toJson(new WeatherDataWrapper(weatherData));
        mPrefs.edit().putString(KEY_LAST_WEATHER_DATA, weatherDataJson).apply();
    }

    public WeatherData getLastWeatherData() {
        if (!mPrefs.contains(KEY_LAST_WEATHER_DATA)) {
            return null;
        }

        String weatherDataJson = mPrefs.getString(KEY_LAST_WEATHER_DATA, null);
        return new Gson().fromJson(weatherDataJson, WeatherDataWrapper.class).toWeatherData();
    }

    private static class WeatherDataWrapper {
        @SerializedName("city")
        @Expose
        String city;
        @SerializedName("kelvin_temperature")
        @Expose
        float kelvinTemperature;
        @SerializedName("weather_type_name")
        @Expose
        String weatherTypeName;
        @SerializedName("timestamp")
        @Expose
        long timestamp;
        @SerializedName("sunrise_timestamp")
        @Expose
        long sunriseTimestamp;
        @SerializedName("sunset_timestamp")
        @Expose
        long sunsetTimestamp;

        WeatherDataWrapper(WeatherData weatherData) {
            city = weatherData.getCity();
            kelvinTemperature = weatherData.getTemperatureK();
            weatherTypeName = weatherData.getWeatherType().name();
            timestamp = weatherData.getTimestamp();
            sunriseTimestamp = weatherData.getSunriseTimestamp();
            sunsetTimestamp = weatherData.getSunsetTimestamp();
        }

        WeatherData toWeatherData() {
            WeatherData weatherData = new WeatherData();
            weatherData.setCity(city);
            weatherData.setTemperatureK(kelvinTemperature);
            weatherData.setWeatherType(WeatherType.valueOf(weatherTypeName));
            weatherData.setTimestamp(timestamp);
            weatherData.setSunriseTimestamp(sunriseTimestamp);
            weatherData.setSunsetTimestamp(sunsetTimestamp);
            return weatherData;
        }
    }
}
