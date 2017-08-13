package com.acbelter.weatherapp.data.repository.preference;

import com.acbelter.weatherapp.domain.repository.SettingsRepo;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class SettingsRepoTest {

    @Mock
    SettingsPreference mockSettingsPreference;

    private SettingsRepo settingsRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        settingsRepo = new SettingsRepoImpl(mockSettingsPreference);
    }

    @Test
    public void testGetUserSettings() {
        settingsRepo.getUserSettings();
        verify(mockSettingsPreference).loadUserSettings();
    }

    @Test
    public void testSaveTemperatureMetric() {
        settingsRepo.saveTemperatureMetric(any(TemperatureMetric.class));
        verify(mockSettingsPreference).saveTemperatureMetric(any(TemperatureMetric.class));
    }

    @Test
    public void testSaveUpdateInterval() {
        settingsRepo.saveUpdateInterval(anyLong());
        verify(mockSettingsPreference).saveUpdateInterval(anyLong());
    }
}
