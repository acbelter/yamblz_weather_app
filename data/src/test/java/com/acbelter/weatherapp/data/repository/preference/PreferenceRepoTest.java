package com.acbelter.weatherapp.data.repository.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.acbelter.weatherapp.domain.model.weather.WeatherData;

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

    private PreferencesRepo preferencesRepo;

    @Before
    public void setUp() {
        SharedPreferences sharedPreferences = RuntimeEnvironment.application.getSharedPreferences("storage", Context.MODE_PRIVATE);
        preferencesRepo = new PreferencesRepo(sharedPreferences);
        sharedPreferences.edit().clear().apply();
    }

    @Test
    public void testDefaultCity() {
        String defaultCity = "Moscow, TN 38057";
        assertEquals(preferencesRepo.getCurrentCity(), defaultCity);
    }

    @Test
    public void testDefaultUpdateInterval() {
        int defaultInterval = 1;
        assertEquals(preferencesRepo.getUpdateInterval(), defaultInterval);
    }

    @Test
    public void testDefaultLastUpdateTimestamp() {
        long defaultLastUpdateTimestamp = 0L;
        assertEquals(preferencesRepo.getLastUpdateTimestamp(), defaultLastUpdateTimestamp);
    }

    @Test
    public void testDefaultLastWeatherData() {
        assertNull(preferencesRepo.getLastWeatherData());
    }

    @Test
    public void testSavedCity() {
        String savedCity = "Moscow";
        preferencesRepo.setCurrentCity(savedCity);
        assertEquals(preferencesRepo.getCurrentCity(), savedCity);
    }

    @Test
    public void testSavedUpdateInterval() {
        int savedInterval = 5;
        preferencesRepo.setUpdateInterval(savedInterval);
        assertEquals(preferencesRepo.getUpdateInterval(), savedInterval);
    }

    @Test
    public void testSavedLastUpdateTimestamp() {
        long saveTimestamp = 5L;
        preferencesRepo.setLastUpdateTimestamp(saveTimestamp);
        assertEquals(preferencesRepo.getLastUpdateTimestamp(), saveTimestamp);
    }

    @Test
    public void testSavedWeatherData() {
        WeatherData savedWeatherData = new WeatherData();
        savedWeatherData.setCity("Moscow");
        savedWeatherData.setTemperatureK(1f);
        savedWeatherData.setWeatherType(SUN);
        savedWeatherData.setTimestamp(2L);
        savedWeatherData.setSunsetTimestamp(3L);
        savedWeatherData.setSunriseTimestamp(4L);
        preferencesRepo.setLastWeatherData(savedWeatherData);
        assertEquals(preferencesRepo.getLastWeatherData(), savedWeatherData);
    }
}
