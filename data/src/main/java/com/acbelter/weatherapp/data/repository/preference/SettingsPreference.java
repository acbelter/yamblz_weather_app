package com.acbelter.weatherapp.data.repository.preference;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.acbelter.weatherapp.domain.model.settings.SettingsData;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;

import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.CELSIUS;
import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.fromString;

public class SettingsPreference {
    public static final String KEY_CURRENT_CITY = "pref_current_city";
    public static final String TEMPERATURE_METRIC_KEY = "pref_temperature_metric";
    public static final String KEY_UPDATE_INTERVAL = "pref_update_interval";
    public static final String KEY_LAST_UPDATE_TIMESTAMP = "pref_last_update_timestamp";
    public static final String KEY_LAST_WEATHER_DATA = "pref_last_weather_data";

    private static final long MIN_UPDATE_INTERVAL = 1 * 60 * 60 * 1000; // interval is 1 hour

    private SharedPreferences sharedPreferences;

    public SettingsPreference(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void addListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        if (listener != null) {
            sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
        }
    }

    public void removeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        if (listener != null) {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
        }
    }

    public void saveCurrentCity(String city) {
        sharedPreferences.edit().putString(KEY_CURRENT_CITY, city).apply();
    }

    public String loadCurrentCity() {
        // FIXME Don't use Moscow as default city
        return sharedPreferences.getString(KEY_CURRENT_CITY, "Moscow, TN 38057");
    }

    public void saveUpdateInterval(long interval) {
        sharedPreferences.edit().putLong(KEY_UPDATE_INTERVAL, interval).apply();
    }

    public long loadUpdateInterval() {
        return sharedPreferences.getLong(KEY_UPDATE_INTERVAL, MIN_UPDATE_INTERVAL);
    }

    public void setLastUpdateTimestamp(long timestamp) {
        sharedPreferences.edit().putLong(KEY_LAST_UPDATE_TIMESTAMP, timestamp).apply();
    }

    public long getLastUpdateTimestamp() {
        return sharedPreferences.getLong(KEY_LAST_UPDATE_TIMESTAMP, 0L);
    }

    public void setLastWeatherData(WeatherData weatherData) {
        if (weatherData == null) {
            sharedPreferences.edit().remove(KEY_LAST_WEATHER_DATA).apply();
            return;
        }

        String weatherDataJson = new Gson().toJson(weatherData);
        sharedPreferences.edit().putString(KEY_LAST_WEATHER_DATA, weatherDataJson).apply();
    }

    public
    @Nullable
    Observable<WeatherData> getLastWeatherData() {
        String weatherDataJson = sharedPreferences.getString(KEY_LAST_WEATHER_DATA, null);
        WeatherData weatherData = new Gson().fromJson(weatherDataJson, WeatherData.class);
        return Observable.fromCallable(() -> weatherData);
    }

    public void saveTemperatureMetric(TemperatureMetric metric) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEMPERATURE_METRIC_KEY, metric.getUnit());
        editor.apply();
    }

    public
    @Nullable
    TemperatureMetric loadTemperatureMetric() {
        String metric = sharedPreferences.getString(TEMPERATURE_METRIC_KEY, CELSIUS.getUnit());
        return fromString(metric);
    }

    public
    @NonNull
    Observable<SettingsData> loadUserSettings() {
        TemperatureMetric metric = loadTemperatureMetric();
        long interval = loadUpdateInterval();
        String cityName = loadCurrentCity();
        return Observable.just(new SettingsData.Builder(metric, interval).cityName(cityName).build());
    }
}