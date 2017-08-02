package com.acbelter.weatherapp.data.network;

import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.subjects.PublishSubject;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherDataApiTest {

    @Mock
    WeatherApi mockWeatherApi;

    @Mock
    PlacesApi mockPlacesApi;

    @Mock
    LocationApi mockLocationApi;

    private NetworkService networkService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.networkService = new NetworkServiceImpl(mockWeatherApi, mockPlacesApi, mockLocationApi);
    }

    @Test
    public void testGetPlaceIdFromApi() {
        WeatherParams weatherParams = new WeatherParams("Mos");

        PublishSubject<String> subject = PublishSubject.create();
        when(mockWeatherApi.getCurrentWeather(anyString(), anyString())).thenReturn(subject);
        networkService.getCurrentWeather(weatherParams);
        verify(mockWeatherApi).getCurrentWeather(anyString(), anyString());
    }
}
