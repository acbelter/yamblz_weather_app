package data;

import com.acbelter.weatherapp.data.network.LocationApi;
import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.data.network.NetworkServiceImpl;
import com.acbelter.weatherapp.data.network.PlacesApi;
import com.acbelter.weatherapp.data.network.WeatherApi;
import com.acbelter.weatherapp.data.repository.PreferencesRepo;
import com.acbelter.weatherapp.data.repository.city.CityRepoImpl;
import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.repository.CityRepo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CityDataApiTest {

    @Mock
    WeatherApi mockWeatherApi;

    @Mock
    PlacesApi mockPlacesApi;

    @Mock
    LocationApi mockLocationApi;

    @Mock
    CityRepo mockCityRepo;

    private NetworkService networkService;

    private CityInteractor cityInteractor;

    @Mock
    private PreferencesRepo preferencesRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.networkService = new NetworkServiceImpl(mockWeatherApi, mockPlacesApi, mockLocationApi);
        this.cityInteractor = new CityInteractor(mockCityRepo);
        this.mockCityRepo = new CityRepoImpl(networkService, preferencesRepo);
    }

//    @Test
    public void testGetPlaceIdFromApi() {
        String partOfCity = "Mos";
        CityParams cityParams = new CityParams(partOfCity);
        networkService.getLocation(cityParams);
        verify(mockPlacesApi).getPlaces(partOfCity);
    }

//    @Test
    public void testGetCityListFromRepo() {
        String partOfCity = "Mos";
        CityParams cityParams = new CityParams(partOfCity);
        this.cityInteractor = new CityInteractor(mockCityRepo);
        cityInteractor.getCityList(cityParams);
        verify(mockCityRepo).getCity(cityParams);
    }

    @Test
    public void test() {

    }
}
