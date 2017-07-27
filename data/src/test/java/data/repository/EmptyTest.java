package data.repository;

import com.acbelter.weatherapp.data.netmodel.NetworkWeatherData;
import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import io.reactivex.subscribers.TestSubscriber;

public class EmptyTest {

    @Mock
    final NetworkService networkService = Mockito.mock(NetworkService.class);

    private TestSubscriber<NetworkWeatherData> testSubscriber = new TestSubscriber<>();

    @Test
    public void emptyTest() {
        WeatherParams weatherParams = new WeatherParams("Moscow");
        networkService.getCurrentWeather(weatherParams);
    }
}
