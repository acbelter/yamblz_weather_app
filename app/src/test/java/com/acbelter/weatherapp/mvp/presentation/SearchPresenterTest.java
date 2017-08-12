package com.acbelter.weatherapp.mvp.presentation;

import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherElement;
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

import io.reactivex.Single;

import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.CELSIUS;
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

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new SearchPresenter(mockCityInteractor, mockWeatherInteractor);
        presenter.onAttach(mockView);
    }

    @Test
    public void testShowCityListSuccess() {
        List<AutocompleteData> list = new ArrayList<>();
        AutocompleteData autocompleteData = new AutocompleteData("Moscow", "id");
        list.add(autocompleteData);
        Single<List<AutocompleteData>> subject = Single.just(list);
        when(mockCityInteractor.getCityList(any(CityParams.class))).thenReturn(subject);

        presenter.showCityList("Moscow");

        verify(mockView).updateCityList(anyListOf(AutocompleteData.class));
    }

    @Test
    public void testShowCityListFailure() {
        Throwable expectedError = new Exception("Some error");
        Single<List<AutocompleteData>> subject = Single.error(expectedError);
        when(mockCityInteractor.getCityList(any(CityParams.class)))
                .thenReturn(subject);

        presenter.showCityList("Moscow");
        verify(mockView).showError();
    }

    @Test
    public void testSaveSelectedCityAndWeather() {
        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        Single<CityData> subject = Single.just(cityData);
        CurrentWeatherFavorites currentWeather = new CurrentWeatherFavorites.Builder(0, cityData, CELSIUS).build();
        ForecastWeatherElement forecastWeatherElement = new ForecastWeatherElement.Builder("date", 18, 19, CELSIUS).build();
        List<ForecastWeatherElement> list = new ArrayList<>();
        list.add(forecastWeatherElement);
        Single<FullWeatherModel> subjectWeather = Single.just(new FullWeatherModel(cityData, currentWeather, list));
        when(mockCityInteractor.getCityData(any(AutocompleteData.class))).thenReturn(subject);
        when(mockWeatherInteractor.getNewWeatherAndSaveToDB()).thenReturn(subjectWeather);

        AutocompleteData autocompleteData = new AutocompleteData("Moscow", "id");
        presenter.saveSelectedCityAndWeather(autocompleteData);

        verify(mockCityInteractor).saveSelectedCity(any(CityData.class));
        verify(mockView).close();
    }
}
