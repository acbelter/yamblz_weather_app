package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.weather.WeatherData;
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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherDataInteractorTest {

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
        WeatherData weatherData = new WeatherData();
        Observable<WeatherData> subject = Observable.just(weatherData);
//        when(mockWeatherRepo.getCurrentWeather(any(WeatherParams.class))).thenReturn(subject);

        WeatherParams weatherParams = new WeatherParams("Mos");
        weatherInteractor.getCurrentWeather(weatherParams);

//        verify(mockWeatherRepo).getCurrentWeather(any(WeatherParams.class));
    }

    @Test
    public void testSendingDataFromRepoToInteractor() {
        WeatherData weatherData = new WeatherData();
        Observable<WeatherData> subject = Observable.just(weatherData);
//        when(mockWeatherRepo.getCurrentWeather(any(WeatherParams.class))).thenReturn(subject);

        WeatherParams weatherParams = new WeatherParams("Moscow");
        TestObserver<WeatherData> observer = weatherInteractor.getCurrentWeather(weatherParams)
                .test();

        testScheduler.triggerActions();
        observer.assertNoErrors()
                .assertValue(weatherData);
    }
}

