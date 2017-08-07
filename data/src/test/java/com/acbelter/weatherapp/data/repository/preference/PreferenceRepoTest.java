package com.acbelter.weatherapp.data.repository.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static com.acbelter.weatherapp.domain.model.weather.WeatherType.SUN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
public class PreferenceRepoTest {

    private SettingsPreference settingsPreference;

    @Before
    public void setUp() {
        SharedPreferences sharedPreferences = RuntimeEnvironment.application.getSharedPreferences("storage", Context.MODE_PRIVATE);
        settingsPreference = new SettingsPreference(sharedPreferences);
        sharedPreferences.edit().clear().apply();
    }

    @Test
    public void testDefaultCity() {
        String defaultCity = "Moscow, TN 38057";
        assertEquals(settingsPreference.loadCurrentCity(), defaultCity);
    }

    @Test
    public void testDefaultUpdateInterval() {
        int defaultInterval = 1;
        assertEquals(settingsPreference.loadUpdateInterval(), defaultInterval);
    }

    @Test
    public void testDefaultLastUpdateTimestamp() {
        long defaultLastUpdateTimestamp = 0L;
        assertEquals(settingsPreference.getLastUpdateTimestamp(), defaultLastUpdateTimestamp);
    }

    @Test
    public void testDefaultLastWeatherData() {
        assertNull(settingsPreference.getLastWeatherData());
    }

    @Test
    public void testSavedCity() {
        String savedCity = "Moscow";
        settingsPreference.saveCurrentCity(savedCity);
        assertEquals(settingsPreference.loadCurrentCity(), savedCity);
    }

    @Test
    public void testSavedUpdateInterval() {
        int savedInterval = 5;
        settingsPreference.saveUpdateInterval(savedInterval);
        assertEquals(settingsPreference.loadUpdateInterval(), savedInterval);
    }

    @Test
    public void testSavedLastUpdateTimestamp() {
        long saveTimestamp = 5L;
        settingsPreference.setLastUpdateTimestamp(saveTimestamp);
        assertEquals(settingsPreference.getLastUpdateTimestamp(), saveTimestamp);
    }

    @Test
    public void testSavedWeatherData() {
        CurrentWeatherFavorites savedCurrentWeatherFavorites = new CurrentWeatherFavorites();
        savedCurrentWeatherFavorites.setCity("Moscow");
        savedCurrentWeatherFavorites.setTemperature(1f);
        savedCurrentWeatherFavorites.setWeatherType(SUN);
        savedCurrentWeatherFavorites.setTimestamp(2L);
        savedCurrentWeatherFavorites.setSunsetTimestamp(3L);
        savedCurrentWeatherFavorites.setSunriseTimestamp(4L);
        settingsPreference.setLastWeatherData(savedCurrentWeatherFavorites);
        assertEquals(settingsPreference.getLastWeatherData(), savedCurrentWeatherFavorites);
    }
}
