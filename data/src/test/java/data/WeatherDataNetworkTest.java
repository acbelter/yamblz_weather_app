package data;

import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
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

import io.reactivex.observers.TestObserver;
import io.reactivex.subjects.PublishSubject;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherDataNetworkTest {

    @Mock
    private WeatherRepo repoMock;

    @InjectMocks
    private WeatherInteractor weatherInteractor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendingDataFromRepoToInteractor() {
        PublishSubject<WeatherData> subject = PublishSubject.create();
        when(repoMock.getCurrentWeather(any(WeatherParams.class))).thenReturn(subject);

        TestObserver<WeatherData> moscow = weatherInteractor.getCurrentWeather(new WeatherParams("Moscow"))
                .test();
        WeatherData weatherData = new WeatherData();
        subject.onNext(weatherData);
        moscow.assertNoErrors()
                .assertValue(weatherData);
    }
}
