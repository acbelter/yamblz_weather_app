package data;

import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmptyTest {

    @Mock
    final NetworkService networkService = Mockito.mock(NetworkService.class);

    @Test
    public void emptyTest() {
        WeatherParams weatherParams = new WeatherParams("Moscow");
        networkService.getCurrentWeather(weatherParams);
    }
}
