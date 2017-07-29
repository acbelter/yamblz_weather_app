package data.city;

import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.repository.CityRepo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.subjects.PublishSubject;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherDataInteractorTest {

    @InjectMocks
    private CityInteractor cityInteractor;

    @Mock
    private CityRepo mockCityRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCityListFromRepo() {
        String partOfCity = "Mos";
        CityParams cityParams = new CityParams(partOfCity);

        PublishSubject<CityData> subject = PublishSubject.create();
        when(mockCityRepo.getCity(any(CityParams.class))).thenReturn(subject);

        cityInteractor.getCityList(cityParams);
        verify(mockCityRepo).getCity(cityParams);
    }
}
