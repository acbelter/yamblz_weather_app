package com.acbelter.weatherapp.data.repository.city;

import com.acbelter.weatherapp.data.locationmodel.Geometry;
import com.acbelter.weatherapp.data.locationmodel.Location;
import com.acbelter.weatherapp.data.locationmodel.Location_;
import com.acbelter.weatherapp.data.locationmodel.Result;
import com.acbelter.weatherapp.data.network.NetworkRepo;
import com.acbelter.weatherapp.data.placesmodel.Places;
import com.acbelter.weatherapp.data.placesmodel.Prediction;
import com.acbelter.weatherapp.data.repository.preference.SettingsPreference;
import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.repository.CityRepo;
import com.acbelter.weatherapp.domain.repository.DatabaseRepo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

import static com.acbelter.weatherapp.data.network.ApiErrors.PlacesApiErrors.OK;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityRepoImplTest {

    @Mock
    private DatabaseRepo mockDatabaseRepo;

    @Mock
    private NetworkRepo mockNetworkRepo;

    @Mock
    private SettingsPreference mockSettingsPreference;

    private CityRepo cityRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cityRepo = new CityRepoImpl(mockNetworkRepo, mockDatabaseRepo, mockSettingsPreference);
    }

    @Test
    public void testGetCityList() {
        Observable<Places> subject = Observable.just(new Places());
        when(mockNetworkRepo.getPlaces(any(CityParams.class))).thenReturn(subject);

        cityRepo.getCityList(any(CityParams.class));

        verify(mockNetworkRepo).getPlaces(any(CityParams.class));
    }

    @Test
    public void testGetCityListFromNetworkRepo() {
        Places places = new Places();
        Prediction prediction = new Prediction();
        prediction.setDescription("Moscow");
        prediction.setPlaceId("id");
        List<Prediction> predictions = new ArrayList<>();
        predictions.add(prediction);
        places.setPredictions(predictions);
        Observable<Places> subject = Observable.just(places);
        when(mockNetworkRepo.getPlaces(any(CityParams.class))).thenReturn(subject);

        TestObserver<List<AutocompleteData>> observer = cityRepo.getCityList(any(CityParams.class))
                .test();

        AutocompleteData autocompleteData = new AutocompleteData("Moscow", "id");
        List<AutocompleteData> list = new ArrayList<>();
        list.add(autocompleteData);
        observer.assertNoErrors()
                .assertValue(list);
    }

    @Test
    public void testGetFavoriteCities() {
        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        List<CityData> list = new ArrayList<>();
        list.add(cityData);
        Flowable<List<CityData>> subject = Flowable.just(list);
        when(mockDatabaseRepo.getAllCities()).thenReturn(subject);

        cityRepo.getFavoritesCities();

        verify(mockDatabaseRepo).getAllCities();
    }

    @Test
    public void testGetFavoriteCitiesFromDatabaseRepo() {
        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        List<CityData> list = new ArrayList<>();
        list.add(cityData);
        Flowable<List<CityData>> subject = Flowable.just(list);
        when(mockDatabaseRepo.getAllCities()).thenReturn(subject);

        TestSubscriber<List<CityData>> observer = cityRepo.getFavoritesCities()
                .test();

        observer.assertNoErrors()
                .assertValue(list);
    }

    @Test
    public void testGetCityData() {
        Single<Location> subject = Single.fromCallable(Location::new);
        when(mockNetworkRepo.getLocation(any(AutocompleteData.class))).thenReturn(subject);

        AutocompleteData autocompleteData = new AutocompleteData("Moscow", "id");
        cityRepo.getCityData(autocompleteData);

        verify(mockNetworkRepo).getLocation(autocompleteData);
    }

    @Test
    public void testGetCityDataFromNetworkRepo() {
        Location location = new Location();
        location.setStatus(OK);
        Result result = new Result();
        Geometry geometry = new Geometry();
        Location_ location_ = new Location_();
        location_.setLat(55.31);
        location_.setLng(54.01);
        geometry.setLocation(location_);
        result.setGeometry(geometry);
        location.setResult(result);

        Single<Location> subject = Single.fromCallable(() -> location);
        when(mockNetworkRepo.getLocation(any(AutocompleteData.class))).thenReturn(subject);

        AutocompleteData autocompleteData = new AutocompleteData("Moscow", "id");
        TestObserver<CityData> observer = cityRepo.getCityData(autocompleteData)
                .test();

        CityData cityData = new CityData.Builder(55.31, 54.01, 0L).build();
        observer.assertNoErrors()
                .assertValue(cityData);
    }

    @Test
    public void testSaveCity() {
        cityRepo.saveCity(any(CityData.class));
        verify(mockSettingsPreference).saveCurrentCity(any(CityData.class));
    }
}
