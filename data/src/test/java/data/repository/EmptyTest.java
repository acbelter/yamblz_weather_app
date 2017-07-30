package data.repository;

import com.acbelter.weatherapp.data.netmodel.NetworkWeatherData;
import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.data.network.WeatherApi;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Observable;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.Mockito.when;

public class EmptyTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    WeatherApi weatherApi;

    @Mock
    NetworkService networkService;

    private TestSubscriber<NetworkWeatherData> testSubscriber = new TestSubscriber<>();

    @Test
    public void emptyTest() {
        WeatherParams weatherParams = new WeatherParams("Moscow");
        networkService.getCurrentWeather(weatherParams);
    }

    @Test
    public void testSubscribe() {
        WeatherParams weatherParams = new WeatherParams("Moscow");
        NetworkWeatherData networkWeatherData = new NetworkWeatherData();
        when(networkService.getCurrentWeather(weatherParams)).thenReturn(Observable.just(networkWeatherData));

//        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
//        assertThat(testSubscriber.getOnNextEvents().get(0)).hasSize(1);
        testSubscriber.assertNotComplete();

    }
}
