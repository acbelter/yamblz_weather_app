package com.acbelter.weatherapp.mvp.presentation;

import android.content.Context;

import com.acbelter.weatherapp.data.repository.preference.SettingsPreference;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherElement;
import com.acbelter.weatherapp.mvp.view.weather.WeatherView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.CELSIUS;
import static org.mockito.Mockito.verify;
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

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new WeatherPresenter(mockWeatherInteractor);
        presenter.onAttach(mockView);
    }

    @Test
    public void testUpdateWeatherSuccess() {
        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        CurrentWeatherFavorites currentWeather = new CurrentWeatherFavorites.Builder(0, cityData, CELSIUS).build();
        ForecastWeatherElement forecastWeatherElement = new ForecastWeatherElement.Builder("date", 18, 19, CELSIUS).build();
        List<ForecastWeatherElement> list = new ArrayList<>();
        list.add(forecastWeatherElement);
        FullWeatherModel fullWeatherModel = new FullWeatherModel(cityData, currentWeather, list);
        Single<FullWeatherModel> subjectWeather = Single.just(fullWeatherModel);
        when(mockWeatherInteractor.updateWeather()).thenReturn(subjectWeather);

        presenter.updateWeather();

        verify(mockView).startLoading();
        verify(mockView).showWeather(fullWeatherModel);
        verify(mockView).stopLoading();
    }

    @Test
    public void testUpdateWeatherError() {
        Throwable expectedError = new Exception("Some error");
        Single<FullWeatherModel> subjectWeather = Single.error(expectedError);
        when(mockWeatherInteractor.updateWeather()).thenReturn(subjectWeather);

        presenter.updateWeather();

        verify(mockView).startLoading();
        verify(mockView).showError();
        verify(mockView).stopLoading();
    }
}
