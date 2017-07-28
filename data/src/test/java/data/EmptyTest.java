package data;

import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class EmptyTest {

    @Mock
    final NetworkService networkService = Mockito.mock(NetworkService.class);

    @Test
    public void emptyTest() {
        WeatherParams weatherParams = new WeatherParams("Moscow");
        networkService.getCurrentWeather(weatherParams);
    }
}
