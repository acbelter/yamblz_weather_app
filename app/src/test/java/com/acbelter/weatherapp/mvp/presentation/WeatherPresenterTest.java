package com.acbelter.weatherapp.mvp.presentation;

import android.content.Context;

import com.acbelter.weatherapp.data.repository.preference.SettingsPreference;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
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
    SettingsPreference mockSettingsPreference;

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
//        verify(mockSettingsPreference).loadCurrentCity();
//        verify(mockView).showError();
    }

    @Test
    public void testUpdateWeatherSuccess() {
        CurrentWeatherFavorites currentWeatherFavorites = new CurrentWeatherFavorites();
        Observable<CurrentWeatherFavorites> subject = Observable.just(currentWeatherFavorites);
        when(mockWeatherInteractor.getCurrentWeather(any(WeatherParams.class))).thenReturn(subject);
        when(mockSettingsPreference.loadCurrentCity()).thenReturn("Moscow");

//        presenter.updateWeather();

//        testScheduler.triggerActions();
//        verify(mockSettingsPreference).loadCurrentCity();
//        verify(mockSettingsPreference).setLastWeatherData(currentWeatherFavorites);
//        verify(mockSettingsPreference).setLastUpdateTimestamp(anyLong());
//        verify(mockView).showWeather(any(CurrentWeatherFavorites.class), anyLong());
    }
}
