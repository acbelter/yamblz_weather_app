package data.city;

import com.acbelter.weatherapp.data.network.LocationApi;
import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.data.network.NetworkServiceImpl;
import com.acbelter.weatherapp.data.network.PlacesApi;
import com.acbelter.weatherapp.data.network.WeatherApi;
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

    private NetworkService networkService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.networkService = new NetworkServiceImpl(mockWeatherApi, mockPlacesApi, mockLocationApi);
    }

    @Test
    public void testGetPlaceIdFromApi() {
        String partOfCity = "Mos";
        CityParams cityParams = new CityParams(partOfCity);

        PublishSubject<Places> subject = PublishSubject.create();
        when(mockPlacesApi.getPlaces(anyString())).thenReturn(subject);
        networkService.getLocation(cityParams);
        verify(mockPlacesApi).getPlaces(partOfCity);
    }
}
