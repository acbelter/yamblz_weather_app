package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.repository.CityRepo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityInteractorTest {

    private CityInteractor cityInteractor;

    @Mock
    private CityRepo mockCityRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        TestScheduler testScheduler = new TestScheduler();
        cityInteractor = new CityInteractor(mockCityRepo, testScheduler, testScheduler);
    }

    @Test
    public void testGetCityList() {
        List<AutocompleteData> dataList = new ArrayList<>();
        Single<List<AutocompleteData>> subject = Single.fromCallable(() -> dataList);
        when(mockCityRepo.getCityList(any(CityParams.class))).thenReturn(subject);

        cityInteractor.getCityList(any(CityParams.class));

        verify(mockCityRepo).getCityList(any(CityParams.class));
    }

    @Test
    public void testGetFavorites() {
        List<CityData> cityDataList = new ArrayList<>();
        Single<List<CityData>> subject = Single.fromCallable(() -> cityDataList);
        when(mockCityRepo.getFavoritesCities()).thenReturn(subject);

        cityInteractor.getFavorites();

        verify(mockCityRepo).getFavoritesCities();
    }

    @Test
    public void testGetCityData() {
        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        Single<CityData> subject = Single.fromCallable(() -> cityData);
        when(mockCityRepo.getCityData(any(AutocompleteData.class))).thenReturn(subject);

        cityInteractor.getCityData(any(AutocompleteData.class));

        verify(mockCityRepo).getCityData(any(AutocompleteData.class));

    }

    @Test
    public void testSaveSelectedCity() {
        cityInteractor.saveSelectedCity(any(CityData.class));

        verify(mockCityRepo).saveCity(any(CityData.class));
    }
}

