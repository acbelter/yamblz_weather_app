package com.acbelter.weatherapp.data.repository.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.settings.SettingsData;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.CELSIUS;
import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.FAHRENHEIT;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class SettingsPreferenceTest {

    private SettingsPreference settingsPreference;

    @Before
    public void setUp() {
        SharedPreferences sharedPreferences = RuntimeEnvironment.application.getSharedPreferences("storage", Context.MODE_PRIVATE);
        settingsPreference = new SettingsPreference(sharedPreferences);
        sharedPreferences.edit().clear().apply();
    }

    @Test
    public void testDefaultCity() {
        CityData defaultCity = new CityData.Builder(55.751244f, 37.618423f, 0L)
                .shortName("Москва")
                .build();
        assertEquals(settingsPreference.loadCurrentCity(), defaultCity);
    }

    @Test
    public void testDefaultUpdateInterval() {
        long defaultInterval = 60 * 60 * 1000;
        assertEquals(settingsPreference.loadUpdateInterval(), defaultInterval);
    }

    @Test
    public void testDefaultTemperatureMetric() {
        assertEquals(settingsPreference.loadTemperatureMetric(), CELSIUS);
    }

    @Test
    public void testDefaultUserSettings() {
        CityData defaultCity = new CityData.Builder(55.751244f, 37.618423f, 0L)
                .shortName("Москва")
                .build();
        long savedInterval = 60 * 60 * 1000;
        SettingsData settingsData = new SettingsData.Builder(CELSIUS, savedInterval)
                .cityData(defaultCity)
                .build();
        assertEquals(settingsPreference.loadUserSettings(), settingsData);
    }

    @Test
    public void testSavedCity() {
        CityData cityData = new CityData.Builder(1f, 2f, 0L)
                .shortName("Москва")
                .build();
        settingsPreference.saveCurrentCity(cityData);
        assertEquals(settingsPreference.loadCurrentCity(), cityData);
    }

    @Test
    public void testSavedUpdateInterval() {
        int savedInterval = 5;
        settingsPreference.saveUpdateInterval(savedInterval);
        assertEquals(settingsPreference.loadUpdateInterval(), savedInterval);
    }

    @Test
    public void testSavedTemperatureMetric() {
        TemperatureMetric temperatureMetric = FAHRENHEIT;
        settingsPreference.saveTemperatureMetric(temperatureMetric);
        assertEquals(settingsPreference.loadTemperatureMetric(), temperatureMetric);
    }

    @Test
    public void testSavedUserSettings() {
        CityData city = new CityData.Builder(1f, 2f, 0L)
                .shortName("Москва")
                .build();
        settingsPreference.saveCurrentCity(city);
        long savedInterval = 1;
        settingsPreference.saveUpdateInterval(savedInterval);
        settingsPreference.saveTemperatureMetric(FAHRENHEIT);
        SettingsData settingsData = new SettingsData.Builder(FAHRENHEIT, savedInterval)
                .cityData(city)
                .build();
        assertEquals(settingsPreference.loadUserSettings(), settingsData);
    }
}
