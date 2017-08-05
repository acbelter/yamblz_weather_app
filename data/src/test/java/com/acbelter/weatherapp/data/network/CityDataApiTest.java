package com.acbelter.weatherapp.data.network;

import com.acbelter.weatherapp.data.placesmodel.Places;
import com.acbelter.weatherapp.domain.model.city.CityParams;

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
public class CityDataApiTest {

    @Mock
    WeatherApi mockWeatherApi;

    @Mock
    PlacesApi mockPlacesApi;

    @Mock
    LocationApi mockLocationApi;

    private NetworkRepo networkRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.networkRepo = new NetworkRepoImpl(mockWeatherApi, mockPlacesApi, mockLocationApi);
    }

    @Test
    public void testGetPlaceIdFromApi() {
        CityParams cityParams = new CityParams("Mos");

        PublishSubject<Places> subject = PublishSubject.create();
        when(mockPlacesApi.getPlaces(anyString())).thenReturn(subject);
        networkRepo.getLocation(cityParams);
        verify(mockPlacesApi).getPlaces(anyString());
    }
}
