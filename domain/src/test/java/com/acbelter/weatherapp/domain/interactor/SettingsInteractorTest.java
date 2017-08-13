package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.repository.SettingsRepo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.CELSIUS;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SettingsInteractorTest {

    private SettingsInteractor settingsInteractor;

    @Mock
    private SettingsRepo mockSettingsRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        settingsInteractor = new SettingsInteractor(mockSettingsRepo);
    }

    @Test
    public void testGetUserSettings() {
        settingsInteractor.getUserSettings();
        verify(mockSettingsRepo).getUserSettings();
    }

    @Test
    public void testSaveTemperatureMetric() {
        settingsInteractor.saveTemperatureMetric(CELSIUS);
        verify(mockSettingsRepo).saveTemperatureMetric(CELSIUS);
    }

    @Test
    public void testSaveUpdateInterval() {
        settingsInteractor.saveUpdateInterval(0L);
        verify(mockSettingsRepo).saveUpdateInterval(0L);
    }
}
