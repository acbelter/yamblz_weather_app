package com.acbelter.weatherapp.presentation;

import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.repository.CityRepo;
import com.acbelter.weatherapp.ui.search.SearchView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchPresenterTest {

    @Mock
    CityInteractor mockCityInteractor;

    @Mock
    WeatherInteractor mockWeatherInteractor;

    @Mock
    SearchView mockView;

    @Mock
    CityRepo mockCityRepo;

    @InjectMocks
    SearchPresenter presenter;

    private TestScheduler testScheduler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testScheduler = new TestScheduler();
        presenter.attachView(mockView);
    }

    @Test
    public void testShowCityListSuccess() {
        List<CityData> list = new ArrayList<>();
        CityData cityData = new CityData();
        cityData.setLongitude(55.31f);
        cityData.setLatitude(54.01f);
        cityData.setFormattedAddress("Moscow, Yandex");
        list.add(cityData);

        Single<List<CityData>> subject = Single.just(list).subscribeOn(testScheduler);
        when(mockCityInteractor.getCityList(any(CityParams.class))).thenReturn(subject);

        presenter.showCityList("Moscow");
        testScheduler.triggerActions();
        verify(mockView).updateCityList(list);
    }

    @Test
    public void testShowCityListFailure() {
        Throwable expectedError = new Exception("Some error");
        Single<List<CityData>> subject = Single.error(expectedError);
        when(mockCityInteractor.getCityList(any(CityParams.class)))
                .thenReturn(subject);

        presenter.showCityList("Moscow");
        verify(mockView).showError();
    }

    @Test
    public void testSaveSelectedCityAndWeather() {
        WeatherData weatherData = new WeatherData();
        Observable<WeatherData> weatherSubject = Observable.just(weatherData).subscribeOn(testScheduler);
        when(mockWeatherInteractor.getCurrentWeather(any(WeatherParams.class))).thenReturn(weatherSubject);

        CityData cityData = new CityData();
        cityData.setFormattedAddress("Moscow, Yandex");
        presenter.saveSelectedCityAndWeather(cityData);

        testScheduler.triggerActions();
        verify(mockCityInteractor).saveSelectedCity(cityData);
        verify(mockWeatherInteractor).getCurrentWeather(any(WeatherParams.class));
        verify(mockWeatherInteractor).saveWeather(weatherData);
        verify(mockView).close();
    }
}
