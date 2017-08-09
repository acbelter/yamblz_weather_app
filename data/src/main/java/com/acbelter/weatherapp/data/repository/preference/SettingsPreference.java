package com.acbelter.weatherapp.data.repository.preference;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.settings.SettingsData;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;
import com.google.gson.Gson;

import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;

import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.CELSIUS;
import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.fromString;

public class SettingsPreference {
    private static final String KEY_CURRENT_CITY = "pref_current_city";
    private static final String TEMPERATURE_METRIC_KEY = "pref_temperature_metric";
    private static final String KEY_UPDATE_INTERVAL = "pref_update_interval";
    private static final String KEY_LAST_UPDATE_TIMESTAMP = "pref_last_update_timestamp";
    private static final String KEY_LAST_WEATHER_DATA = "pref_last_weather_data";

    @NonNull
    private static final long MIN_UPDATE_INTERVAL = 60 * 60 * 1000; // interval is 1 hour

    @NonNull
    private final SharedPreferences sharedPreferences;

    public SettingsPreference(@NonNull SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveCurrentCity(@NonNull CityData cityData) {
        String cityDataStr = new Gson().toJson(cityData);
        sharedPreferences.edit().putString(KEY_CURRENT_CITY, cityDataStr).apply();
    }

    @NonNull
    public CityData loadCurrentCity() {
        CityData defaultCityData;
        if (Locale.getDefault().getLanguage().equals("ru"))
            defaultCityData = new CityData.Builder(55.751244f, 37.618423f)
                    .shortName("Москва")
                    .build();
        else
            defaultCityData = new CityData.Builder(55.751244f, 37.618423f)
                    .shortName("Moscow")
                    .build();
        String defaultStr = new Gson().toJson(defaultCityData);

        String cityDataStr = sharedPreferences.getString(KEY_CURRENT_CITY, defaultStr);
        return new Gson().fromJson(cityDataStr, CityData.class);
    }

    void saveUpdateInterval(@NonNull long interval) {
        sharedPreferences.edit().putLong(KEY_UPDATE_INTERVAL, interval).apply();
    }

    @NonNull
    long loadUpdateInterval() {
        return sharedPreferences.getLong(KEY_UPDATE_INTERVAL, MIN_UPDATE_INTERVAL);
    }

    public void setLastUpdateTimestamp(@NonNull long timestamp) {
        sharedPreferences.edit().putLong(KEY_LAST_UPDATE_TIMESTAMP, timestamp).apply();
    }

    public
    @NonNull
    long getLastUpdateTimestamp() {
        return sharedPreferences.getLong(KEY_LAST_UPDATE_TIMESTAMP, 0L);
    }

    public void saveTemperatureMetric(@NonNull TemperatureMetric metric) {
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

    @NonNull
    Observable<SettingsData> loadUserSettings() {
        TemperatureMetric metric = loadTemperatureMetric();
        long interval = loadUpdateInterval();
        CityData cityData = loadCurrentCity();
        return Observable.fromCallable(() -> new SettingsData.Builder(metric, interval).cityData(cityData).build());
    }
}