package com.acbelter.weatherapp.presentation;

import android.content.Context;

import com.acbelter.weatherapp.data.repository.preference.PreferencesRepo;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.ui.weather.WeatherView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherPresenterTest {

    @Mock
    WeatherInteractor mockWeatherInteractor;

    @Mock
    Context mockContext;

    @Mock
    PreferencesRepo mockPreferencesRepo;

    @Mock
    WeatherView mockView;

    @InjectMocks
    WeatherPresenter presenter;

    private TestScheduler testScheduler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testScheduler = new TestScheduler();
        presenter.attachView(mockView);
    }

    @Test
    public void testUpdateWeatherError() {
//        presenter.updateWeather();

//        testScheduler.triggerActions();
//        verify(mockPreferencesRepo).getCurrentCity();
//        verify(mockView).showError();
    }

    @Test
    public void testUpdateWeatherSuccess() {
        WeatherData weatherData = new WeatherData();
        Observable<WeatherData> subject = Observable.just(weatherData);
        when(mockWeatherInteractor.getCurrentWeather(any(WeatherParams.class))).thenReturn(subject);
        when(mockPreferencesRepo.getCurrentCity()).thenReturn("Moscow");

//        presenter.updateWeather();

//        testScheduler.triggerActions();
//        verify(mockPreferencesRepo).getCurrentCity();
//        verify(mockPreferencesRepo).setLastWeatherData(weatherData);
//        verify(mockPreferencesRepo).setLastUpdateTimestamp(anyLong());
//        verify(mockView).showWeather(any(WeatherData.class), anyLong());
    }
}
