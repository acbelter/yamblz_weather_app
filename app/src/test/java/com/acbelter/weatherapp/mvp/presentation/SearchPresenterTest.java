package com.acbelter.weatherapp.mvp.presentation;

import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherData;
import com.acbelter.weatherapp.ui.search.SearchView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchPresenterTest {

    @Mock
    CityInteractor mockCityInteractor;

    @Mock
    WeatherInteractor mockWeatherInteractor;

    @Mock
    SearchView mockView;

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
        list.add(cityData);

        Single<List<CityData>> subject = Single.just(list);
        when(mockCityInteractor.getCityList(any(CityParams.class))).thenReturn(subject);

        presenter.showCityList("Moscow");
        testScheduler.triggerActions();
        verify(mockView).updateCityList(anyListOf(CityData.class));
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
        CurrentWeatherData currentWeatherData = new CurrentWeatherData();
        Observable<CurrentWeatherData> weatherSubject = Observable.just(currentWeatherData);
//        when(mockWeatherInteractor.getCurrentWeatherData(any(WeatherParams.class))).thenReturn(weatherSubject);

        CityData cityData = new CityData();
        presenter.saveSelectedCityAndWeather(cityData);

        testScheduler.triggerActions();
        verify(mockCityInteractor).saveSelectedCity(any(CityData.class));
//        verify(mockWeatherInteractor).getCurrentWeatherData(any(WeatherParams.class));
        verify(mockWeatherInteractor).saveWeather(any(CurrentWeatherData.class));
        verify(mockView).close();
    }
}
