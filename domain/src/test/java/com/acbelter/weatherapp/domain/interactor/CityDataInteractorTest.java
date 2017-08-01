package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.repository.CityRepo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityDataInteractorTest {

    private CityInteractor cityInteractor;

    @Mock
    private CityRepo mockCityRepo;

    private TestScheduler testScheduler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        testScheduler = new TestScheduler();
        cityInteractor = new CityInteractor(mockCityRepo, testScheduler, testScheduler);
    }

    @Test
    public void testGetCityListFromRepo() {
        CityParams cityParams = new CityParams("Mos");

        Observable<CityData> subject = Observable.just(new CityData());
        when(mockCityRepo.getCity(any(CityParams.class))).thenReturn(subject);

        cityInteractor.getCityList(cityParams);

        testScheduler.triggerActions();
        verify(mockCityRepo).getCity(any(CityParams.class));
    }

    @Test
    public void testSendingDataFromRepoToInteractor() {
        when(mockCityRepo.getCity(any(CityParams.class))).thenReturn(Observable.just(new CityData()));

        TestObserver<List<CityData>> observer = cityInteractor.getCityList(new CityParams("Moscow")).test();

        testScheduler.triggerActions();
        observer.assertNoErrors()
                .assertValue(l -> l.size() == 1);
    }
}

