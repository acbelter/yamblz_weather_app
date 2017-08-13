package com.acbelter.weatherapp.data.repository.weather;

import com.acbelter.weatherapp.data.network.NetworkRepo;
import com.acbelter.weatherapp.data.repository.preference.SettingsPreference;
import com.acbelter.weatherapp.domain.repository.DatabaseRepo;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WeatherRepoTest {

    @Mock
    NetworkRepo mockNetworkRepo;
    @Mock
    SettingsPreference mockSettingsPreference;
    @Mock
    DatabaseRepo mockDatabaseRepo;

    private WeatherRepo weatherRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        weatherRepo = new WeatherRepoImpl(mockNetworkRepo, mockSettingsPreference, mockDatabaseRepo);
    }

    @Test
    public void testGetCurrentWeather() {
//        CityData cityData = new CityData.Builder(52.72, 41.44, 0L).build();
//        CurrentWeatherFavorites currentWeatherFavorites = new CurrentWeatherFavorites.Builder(20, cityData, CELSIUS)
//                .build();
//        CurrentWeather currentWeather = new Gson().fromJson("{\"coord\":{\"lon\":41.44,\"lat\":52.72},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"02d\"}],\"base\":\"stations\",\"main\":{\"temp\":301.124,\"pressure\":1012.19,\"humidity\":40,\"temp_min\":301.124,\"temp_max\":301.124,\"sea_level\":1030.76,\"grnd_level\":1012.19},\"wind\":{\"speed\":3.65,\"deg\":264.501},\"clouds\":{\"all\":8},\"dt\":1502457955,\"sys\":{\"message\":0.0025,\"country\":\"RU\",\"sunrise\":1502416221,\"sunset\":1502470031},\"id\":484646,\"name\":\"Tambov\",\"cod\":200}", CurrentWeather.class);
//        Single<CurrentWeatherFavorites> subjectDB = Single.fromCallable(() -> currentWeatherFavorites);
//        Single<CurrentWeather> subjectNW = Single.fromCallable(() -> currentWeather);
//        when(mockDatabaseRepo.getCurrentWeather(cityData)).thenReturn(subjectDB);
//        WeatherParams weatherParams = new WeatherParams(cityData, CELSIUS);
//        when(mockNetworkRepo.getCurrentWeather(weatherParams)).thenReturn(subjectNW);
//
//        weatherRepo.getCurrentWeather();
//
//        verify(mockDatabaseRepo).getCurrentWeather(cityData);
    }
}
