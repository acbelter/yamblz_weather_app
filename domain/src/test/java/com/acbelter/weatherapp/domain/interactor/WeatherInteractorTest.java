package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherElement;
import com.acbelter.weatherapp.domain.repository.DatabaseRepo;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.CELSIUS;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherInteractorTest {

    private WeatherInteractor weatherInteractor;

    @Mock
    private WeatherRepo mockWeatherRepo;
    @Mock
    private DatabaseRepo mockDatabaseRepo;

    private TestScheduler testScheduler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        testScheduler = new TestScheduler();
        weatherInteractor = new WeatherInteractor(mockWeatherRepo, mockDatabaseRepo, testScheduler, testScheduler);
    }

    @Test
    public void testGetCurrentWeatherFromRepo() {
        weatherInteractor.getCurrentWeather();
        verify(mockWeatherRepo).getCurrentWeather();
    }

    @Test
    public void testUpdateCurrentWeatherFromRepo() {
        weatherInteractor.updateCurrenWeather();
        verify(mockWeatherRepo).updateCurrentWeather();
    }

    @Test
    public void testSendCurrentWeatherFromRepoToInteractor() {
        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        CurrentWeatherFavorites currentWeatherFavorites = new CurrentWeatherFavorites.Builder(0L, cityData, CELSIUS).build();
        Single<CurrentWeatherFavorites> subject = Single.fromCallable(() -> currentWeatherFavorites);
        when(mockWeatherRepo.getCurrentWeather()).thenReturn(subject);

        TestObserver<CurrentWeatherFavorites> observer = weatherInteractor.getCurrentWeather()
                .test();

        testScheduler.triggerActions();
        observer.assertNoErrors()
                .assertValue(currentWeatherFavorites);
    }

    @Test
    public void testUpdateCurrentWeatherFromRepoToInteractor() {
        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        CurrentWeatherFavorites currentWeatherFavorites = new CurrentWeatherFavorites.Builder(0L, cityData, CELSIUS).build();
        Single<CurrentWeatherFavorites> subject = Single.fromCallable(() -> currentWeatherFavorites);
        when(mockWeatherRepo.updateCurrentWeather()).thenReturn(subject);

        TestObserver<CurrentWeatherFavorites> observer = weatherInteractor.updateCurrenWeather()
                .test();

        testScheduler.triggerActions();
        observer.assertNoErrors()
                .assertValue(currentWeatherFavorites);
    }

    @Test
    public void testGetForecastFromRepo() {
        weatherInteractor.getForecast();
        verify(mockWeatherRepo).getForecast();
    }

    @Test
    public void testUpdateForecastFromRepo() {
        weatherInteractor.updateForecast();
        verify(mockWeatherRepo).updateForecast();
    }

    @Test
    public void testSendForecastFromRepoToInteractor() {
        List<ForecastWeatherElement> forecast = new ArrayList<>();
        Single<List<ForecastWeatherElement>> subject = Single.fromCallable(() -> forecast);
        when(mockWeatherRepo.getForecast()).thenReturn(subject);

        TestObserver<List<ForecastWeatherElement>> observer = weatherInteractor.getForecast()
                .test();

        testScheduler.triggerActions();
        observer.assertNoErrors()
                .assertValue(forecast);
    }

    @Test
    public void testUpdateForecastFromRepoToInteractor() {
        List<ForecastWeatherElement> forecast = new ArrayList<>();
        Single<List<ForecastWeatherElement>> subject = Single.fromCallable(() -> forecast);
        when(mockWeatherRepo.updateForecast()).thenReturn(subject);

        TestObserver<List<ForecastWeatherElement>> observer = weatherInteractor.updateForecast()
                .test();

        testScheduler.triggerActions();
        observer.assertNoErrors()
                .assertValue(forecast);
    }

    @Test
    public void testGetWeather() {
//        CityData cityData = new CityData.Builder(0, 0, 0L).build();
//        CurrentWeatherFavorites currentWeatherFavorites = new CurrentWeatherFavorites.Builder(0, cityData, CELSIUS).build();
//        ForecastWeatherElement forecastWeatherElement = new ForecastWeatherElement.Builder("today", 0, 0, CELSIUS).build();
//        List<ForecastWeatherElement> list = new ArrayList<>();
//        list.add(forecastWeatherElement);
//        FullWeatherModel fullWeatherModel = new FullWeatherModel(cityData, currentWeatherFavorites, list);
//
//        Single<CurrentWeatherFavorites> currentSubject = Single.fromCallable(() -> currentWeatherFavorites);
//        Single<List<ForecastWeatherElement>> forecastSubject = Single.fromCallable(() -> list);
//        when(mockWeatherRepo.getCurrentWeather()).thenReturn(currentSubject);
//        when(mockWeatherRepo.getForecast()).thenReturn(forecastSubject);
////        when(WeatherInteractor.convertCachedWeather(currentWeatherFavorites, list)).thenReturn(fullWeatherModel);
//
//
//        weatherInteractor.getWeather();
//
//        verify(mockWeatherRepo).getCurrentWeather();
//        verify(mockWeatherRepo).getForecast();
    }

    @Test
    public void testConvertCachedWeather() {
//        CityData cityData = new CityData.Builder(0, 0, 0L).build();
//        CurrentWeatherFavorites currentWeatherFavorites = new CurrentWeatherFavorites.Builder(0, cityData, CELSIUS).build();
//        ForecastWeatherElement forecastWeatherElement = new ForecastWeatherElement.Builder("today", 0, 0, CELSIUS).build();
//        List<ForecastWeatherElement> list = new ArrayList<>();
//        list.add(forecastWeatherElement);
//        FullWeatherModel fullWeatherModel = weatherInteractor.convertCachedWeather(currentWeatherFavorites, list);
//
//        assertEquals(any(FullWeatherModel.class), weatherInteractor.convertCachedWeather(currentWeatherFavorites, list));
    }
}
