package data.repository;

import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.subjects.PublishSubject;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherDataNetworkTest {

    private WeatherRepo repoMock;
    private WeatherInteractor weatherInteractor;

    @Before
    public void setup() {
        repoMock = mock(WeatherRepo.class, new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                if (invocation.getMethod().getReturnType().isInstance(Observable.class)) {
                    return PublishSubject.create();
                }
                return null;
            }
        });

        weatherInteractor = new WeatherInteractor(repoMock);
    }

    @Test
    public void testSendingDataFromRepoToInteractor() {
        WeatherData weatherData = new WeatherData();
        PublishSubject<WeatherData> subject = PublishSubject.create();
        when(repoMock.getCurrentWeather(any(WeatherParams.class))).thenReturn(subject);
        TestObserver<WeatherData> moscow = weatherInteractor.getCurrentWeather(new WeatherParams("Moscow"))
                .test();
        subject.onNext(weatherData);
        moscow.assertNoErrors()
                .assertValue(weatherData);
    }
}
