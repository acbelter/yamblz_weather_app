package com.acbelter.weatherapp.mvp.presentation;

import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.mvp.view.search.SearchView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.schedulers.TestScheduler;

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
//        presenter.attachView(mockView);
    }

    @Test
    public void testShowCityListSuccess() {
        List<CityData> list = new ArrayList<>();
//        CityData cityData = new CityData();
//        list.add(cityData);
//
//        Single<List<CityData>> subject = Single.just(list);
//        when(mockCityInteractor.getCityList(any(CityParams.class))).thenReturn(subject);
//
//        presenter.showCityList("Moscow");
//        testScheduler.triggerActions();
//        verify(mockView).updateCityList(anyListOf(CityData.class));
    }

    @Test
    public void testShowCityListFailure() {
//        Throwable expectedError = new Exception("Some error");
//        Single<List<CityData>> subject = Single.error(expectedError);
//        when(mockCityInteractor.getCityList(any(CityParams.class)))
//                .thenReturn(subject);
//
//        presenter.showCityList("Moscow");
//        verify(mockView).showError();
    }

    @Test
    public void testSaveSelectedCityAndWeather() {
//        CurrentWeatherFavorites currentWeatherFavorites = new CurrentWeatherFavorites();
//        Observable<CurrentWeatherFavorites> weatherSubject = Observable.just(currentWeatherFavorites);
//        when(mockWeatherInteractor.getCurrentWeatherFavorites(any(WeatherParams.class))).thenReturn(weatherSubject);
//
//        CityData cityData = new CityData();
//        presenter.saveSelectedCityAndWeather(cityData);
//
//        testScheduler.triggerActions();
//        verify(mockCityInteractor).saveSelectedCity(any(CityData.class));
//        verify(mockWeatherInteractor).getCurrentWeatherFavorites(any(WeatherParams.class));
//        verify(mockWeatherInteractor).saveWeather(any(CurrentWeatherFavorites.class));
//        verify(mockView).close();
    }
}
