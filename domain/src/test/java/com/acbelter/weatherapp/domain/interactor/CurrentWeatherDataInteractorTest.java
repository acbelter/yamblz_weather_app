package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherData;
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
public class CurrentWeatherDataInteractorTest {

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
        CurrentWeatherData currentWeatherData = new CurrentWeatherData();
        Observable<CurrentWeatherData> subject = Observable.just(currentWeatherData);
//        when(mockWeatherRepo.getCurrentWeatherData(any(WeatherParams.class))).thenReturn(subject);

        WeatherParams weatherParams = new WeatherParams("Mos");
        weatherInteractor.getCurrentWeather(weatherParams);

//        verify(mockWeatherRepo).getCurrentWeatherData(any(WeatherParams.class));
    }

    @Test
    public void testSendingDataFromRepoToInteractor() {
        CurrentWeatherData currentWeatherData = new CurrentWeatherData();
        Observable<CurrentWeatherData> subject = Observable.just(currentWeatherData);
//        when(mockWeatherRepo.getCurrentWeatherData(any(WeatherParams.class))).thenReturn(subject);

        WeatherParams weatherParams = new WeatherParams("Moscow");
        TestObserver<CurrentWeatherData> observer = weatherInteractor.getCurrentWeather(weatherParams)
                .test();

        testScheduler.triggerActions();
        observer.assertNoErrors()
                .assertValue(currentWeatherData);
    }
}

