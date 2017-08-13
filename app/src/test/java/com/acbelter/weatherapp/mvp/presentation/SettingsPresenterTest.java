package com.acbelter.weatherapp.mvp.presentation;

import com.acbelter.weatherapp.domain.interactor.SettingsInteractor;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;
import com.acbelter.weatherapp.mvp.view.settings.SettingsView;
import com.acbelter.weatherapp.scheduler.WeatherScheduleJob;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SettingsPresenterTest {

    @Mock
    SettingsInteractor mockSettingsInteractor;
    @Mock
    WeatherScheduleJob mockWeatherScheduleJob;
    @Mock
    SettingsView mockView;

    @InjectMocks
    SettingsPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new SettingsPresenter(mockSettingsInteractor, mockWeatherScheduleJob);
        presenter.onAttach(mockView);
    }

    @Test
    public void testShowSettings() {
        presenter.showSettings();
        verify(mockView).setSettings(mockSettingsInteractor.getUserSettings());
    }

    @Test
    public void testSaveTemperatureMetric() {
        presenter.saveTemperatureMetric(any(TemperatureMetric.class));
        verify(mockSettingsInteractor).saveTemperatureMetric(any(TemperatureMetric.class));
    }

    @Test
    public void testSaveUpdateInterval() {
        presenter.saveUpdateInterval(anyLong());
        verify(mockSettingsInteractor).saveUpdateInterval(anyLong());
        verify(mockWeatherScheduleJob).startJob();
    }

}
