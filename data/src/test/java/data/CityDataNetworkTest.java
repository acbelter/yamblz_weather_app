package data;


import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.repository.CityRepo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityDataNetworkTest {

    @Mock
    CityRepo cityRepo;

    @InjectMocks
    CityInteractor cityInteractor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSubsribe() {
        when(cityRepo.getCity(any(CityParams.class))).thenReturn(Observable.just(new CityData()));

        cityInteractor.getCityList(new CityParams("Moscow")).test()
                .assertNoErrors()
                .assertValue(l -> l.size() == 1);
    }

}
