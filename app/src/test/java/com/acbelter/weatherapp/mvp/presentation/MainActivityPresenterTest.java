package com.acbelter.weatherapp.mvp.presentation;

import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.interactor.SettingsInteractor;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.settings.SettingsData;
import com.acbelter.weatherapp.domain.repository.SettingsRepo;
import com.acbelter.weatherapp.mvp.view.activity.MainActivityView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.CELSIUS;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest {

    @Mock
    CityInteractor mockCityInteractor;
    @Mock
    WeatherInteractor mockWeatherInteractor;
    @Mock
    SettingsInteractor mockSettingsInteractor;
    @Mock
    MainActivityView mockView;
    @Mock
    SettingsData mockSettingsData;
    @Mock
    SettingsRepo mockSettingsRepo;

    @InjectMocks
    MainActivityPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainActivityPresenter(mockCityInteractor, mockWeatherInteractor, mockSettingsInteractor);
        presenter.onAttach(mockView);
    }

    @Test
    public void testShowCityList() {
        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        List<CityData> list = new ArrayList<>();
        list.add(cityData);
        Flowable<List<CityData>> subject = Flowable.fromCallable(() -> list);
        when(mockCityInteractor.getFavorites()).thenReturn(subject);
        SettingsData settingsData = new SettingsData.Builder(CELSIUS, 0L).build();
        when(mockSettingsInteractor.getUserSettings()).thenReturn(settingsData);

        presenter.showCityList();

        verify(mockCityInteractor).getFavorites();
        verify(mockView).showCityList(list);
    }

    @Test
    public void testShowSelectedWeather() {
        presenter.showSelectedWeather(any(CityData.class));
        verify(mockCityInteractor).saveSelectedCity(any(CityData.class));
    }

    @Test
    public void testDeleteItem() {
        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        List<CityData> list = new ArrayList<>();
        list.add(cityData);
        Flowable<List<CityData>> subject = Flowable.fromCallable(() -> list);
        when(mockCityInteractor.getFavorites()).thenReturn(subject);
        SettingsData settingsData = new SettingsData.Builder(CELSIUS, 0L).build();
        when(mockSettingsInteractor.getUserSettings()).thenReturn(settingsData);

        presenter.deleteItem(cityData);

        verify(mockCityInteractor).getFavorites();
    }
}
