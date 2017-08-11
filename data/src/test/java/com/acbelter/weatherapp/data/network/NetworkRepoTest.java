package com.acbelter.weatherapp.data.network;

import com.acbelter.weatherapp.data.locationmodel.Location;
import com.acbelter.weatherapp.data.placesmodel.Places;
import com.acbelter.weatherapp.data.weathermodel.currentweather.CurrentWeather;
import com.acbelter.weatherapp.data.weathermodel.forecast.ForecastWeather;
import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.subjects.PublishSubject;

import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.CELSIUS;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NetworkRepoTest {

    @Mock
    WeatherApi mockWeatherApi;

    @Mock
    PlacesApi mockPlacesApi;

    @Mock
    LocationApi mockLocationApi;

    private NetworkRepo networkRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.networkRepo = new NetworkRepoImpl(mockWeatherApi, mockPlacesApi, mockLocationApi);
    }

    @Test
    public void testGetCurrentWeather() {
        CurrentWeather currentWeather = new CurrentWeather();
        Single<CurrentWeather> subject = Single.fromCallable(() -> currentWeather);
        when(mockWeatherApi.getCurrentWeather(anyDouble(), anyDouble(), anyString())).thenReturn(subject);

        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        WeatherParams weatherParams = new WeatherParams(cityData, CELSIUS);
        networkRepo.getCurrentWeather(weatherParams);

        verify(mockWeatherApi).getCurrentWeather(anyDouble(), anyDouble(), anyString());
    }

    @Test
    public void testGetCurrentWeatherData() {
        CurrentWeather weather = new CurrentWeather();
        Single<CurrentWeather> subject = Single.fromCallable(() -> weather);
        when(mockWeatherApi.getCurrentWeather(anyDouble(), anyDouble(), anyString())).thenReturn(subject);

        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        WeatherParams weatherParams = new WeatherParams(cityData, CELSIUS);
        TestObserver<CurrentWeather> observer = networkRepo.getCurrentWeather(weatherParams)
                .test();

        observer.assertNoErrors()
                .assertValue(weather);
    }

    @Test
    public void testGetForecastWeather() {
        ForecastWeather forecastWeather = new ForecastWeather();
        Single<ForecastWeather> subject = Single.fromCallable(() -> forecastWeather);
        when(mockWeatherApi.getForecast(anyDouble(), anyDouble(), anyString())).thenReturn(subject);

        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        WeatherParams weatherParams = new WeatherParams(cityData, CELSIUS);
        networkRepo.getForecastWeather(weatherParams);

        verify(mockWeatherApi).getForecast(anyDouble(), anyDouble(), anyString());
    }

    @Test
    public void testGetForecastWEatherData() {
        ForecastWeather weather = new ForecastWeather();
        Single<ForecastWeather> subject = Single.fromCallable(() -> weather);
        when(mockWeatherApi.getForecast(anyDouble(), anyDouble(), anyString())).thenReturn(subject);

        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        WeatherParams weatherParams = new WeatherParams(cityData, CELSIUS);
        TestObserver<ForecastWeather> observer = networkRepo.getForecastWeather(weatherParams)
                .test();

        observer.assertNoErrors()
                .assertValue(weather);
    }

    @Test
    public void testGetPlaces() {
        PublishSubject<Places> subject = PublishSubject.create();
        when(mockPlacesApi.getPlaces(anyString())).thenReturn(subject);

        CityParams cityParams = new CityParams("Mos");
        networkRepo.getPlaces(cityParams);

        verify(mockPlacesApi).getPlaces(anyString());
    }

    @Test
    public void testGetPlacesData() {
        Places places = new Places();
        Observable<Places> subject = Observable.just(places);
        when(mockPlacesApi.getPlaces(anyString())).thenReturn(subject);

        CityParams cityParams = new CityParams("Mos");
        TestObserver<Places> observer = networkRepo.getPlaces(cityParams)
                .test();

        observer.assertNoErrors()
                .assertValue(places);
    }

    @Test
    public void testGetLocation() {
        Location location = new Location();
        Single<Location> subject = Single.fromCallable(() -> location);
        when(mockLocationApi.getLocation(anyString(), anyString())).thenReturn(subject);

        AutocompleteData autocompleteData = new AutocompleteData("Moscow", "id");
        networkRepo.getLocation(autocompleteData);

        verify(mockLocationApi).getLocation("id", "ru");
    }

    @Test
    public void testGetLocationData() {
        Location location = new Location();
        Single<Location> subject = Single.fromCallable(() -> location);
        when(mockLocationApi.getLocation(anyString(), anyString())).thenReturn(subject);

        AutocompleteData data = new AutocompleteData("Moscow", "id");
        TestObserver<Location> observer = networkRepo.getLocation(data)
                .test();

        observer.assertNoErrors()
                .assertValue(location);
    }
}
