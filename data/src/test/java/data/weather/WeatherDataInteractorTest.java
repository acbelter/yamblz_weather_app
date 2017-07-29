package data.weather;

import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

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
    private WeatherInteractor weatherInteractor;

    @Mock
    private WeatherRepo mockWeatherRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCityListFromRepo() {
        String partOfCity = "Mos";
        WeatherParams weatherParams = new WeatherParams(partOfCity);

        PublishSubject<WeatherData> subject = PublishSubject.create();
        when(mockWeatherRepo.getCurrentWeather(any(WeatherParams.class))).thenReturn(subject);

        weatherInteractor.getCurrentWeather(weatherParams);
        verify(mockWeatherRepo).getCurrentWeather(weatherParams);
    }
}
