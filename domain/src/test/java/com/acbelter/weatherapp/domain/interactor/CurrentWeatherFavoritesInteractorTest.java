package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

@RunWith(MockitoJUnitRunner.class)
public class CurrentWeatherFavoritesInteractorTest {

    private WeatherInteractor weatherInteractor;

    @Mock
    private WeatherRepo mockWeatherRepo;

    private TestScheduler testScheduler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        testScheduler = new TestScheduler();
        weatherInteractor = new WeatherInteractor(mockWeatherRepo, testScheduler, testScheduler);
    }

    @Test
    public void testGetCityListFromRepo() {
        CurrentWeatherFavorites currentWeatherFavorites = new CurrentWeatherFavorites();
        Observable<CurrentWeatherFavorites> subject = Observable.just(currentWeatherFavorites);
//        when(mockWeatherRepo.getCurrentWeatherFavorites(any(WeatherParams.class))).thenReturn(subject);

        WeatherParams weatherParams = new WeatherParams("Mos");
        weatherInteractor.getCurrentWeather(weatherParams);

//        verify(mockWeatherRepo).getCurrentWeatherFavorites(any(WeatherParams.class));
    }

    @Test
    public void testSendingDataFromRepoToInteractor() {
        CurrentWeatherFavorites currentWeatherFavorites = new CurrentWeatherFavorites();
        Observable<CurrentWeatherFavorites> subject = Observable.just(currentWeatherFavorites);
//        when(mockWeatherRepo.getCurrentWeatherFavorites(any(WeatherParams.class))).thenReturn(subject);

        WeatherParams weatherParams = new WeatherParams("Moscow");
        TestObserver<CurrentWeatherFavorites> observer = weatherInteractor.getCurrentWeather(weatherParams)
                .test();

        testScheduler.triggerActions();
        observer.assertNoErrors()
                .assertValue(currentWeatherFavorites);
    }
}

