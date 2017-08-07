package com.acbelter.weatherapp.data.repository.weather;

import com.acbelter.weatherapp.data.weathermodel.currentweather.Sys;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.WeatherType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class CurrentWeatherFavoritesConverterTest {

    private com.acbelter.weatherapp.data.weathermodel.currentweather.CurrentWeather currentWeather;
    private CurrentWeatherFavorites currentWeatherFavorites;

    @Before
    public void setUp() {
        currentWeatherFavorites = new com.acbelter.weatherapp.data.weathermodel.currentweather.CurrentWeather();
        currentWeatherFavorites = new CurrentWeatherFavorites();
    }

    @Test
    public void testCodeError() {
        currentWeatherFavorites.code = 201;
        assertNull(WeatherDataConverter.fromNWWeatherDataToCurrentWeatherData(currentWeatherFavorites));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWeatherConverterToNull() {
        assertNull(WeatherDataConverter.fromNWWeatherDataToCurrentWeatherData(null));
    }

    @Test
    public void testExtractWeatherThunderstormType() {
        Weather weather = new Weather();
        weather.main = "Thunderstorm";
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        assertEquals(WeatherDataConverter.extractWeatherType(weatherList), WeatherType.STORM);
    }

    @Test
    public void testExtractWeatherRainType() {
        Weather weather = new Weather();
        weather.main = "Rain";
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        assertEquals(WeatherDataConverter.extractWeatherType(weatherList), WeatherType.RAIN);
    }

    @Test
    public void testExtractWeatherDrizzleType() {
        Weather weather = new Weather();
        weather.main = "Drizzle";
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        assertEquals(WeatherDataConverter.extractWeatherType(weatherList), WeatherType.RAIN);
    }

    @Test
    public void testExtractWeatherSnowType() {
        Weather weather = new Weather();
        weather.main = "Snow";
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        assertEquals(WeatherDataConverter.extractWeatherType(weatherList), WeatherType.SNOW);
    }

    @Test
    public void testExtractWeatherCloudsType() {
        Weather weather = new Weather();
        weather.main = "Clouds";
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        assertEquals(WeatherDataConverter.extractWeatherType(weatherList), WeatherType.CLOUDS);
    }

    @Test
    public void testExtractWeatherSunType() {
        Weather weather = new Weather();
        weather.main = "Something";
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        assertEquals(WeatherDataConverter.extractWeatherType(weatherList), WeatherType.SUN);
    }

    @Test
    public void testConverting() {
        initNetworkWeather();
        initWeatherData();

        CurrentWeatherFavorites testCurrentWeatherFavorites = WeatherDataConverter.fromNWWeatherDataToCurrentWeatherData(currentWeatherFavorites);
        assertEquals(testCurrentWeatherFavorites.getCity(), currentWeatherFavorites.getCity());
    }

    private void initNetworkWeather() {
        currentWeatherFavorites.code = 200;
        currentWeatherFavorites.name = "Tambov";
        Weather weather = new Weather();
        weather.main = "Clear";
        currentWeatherFavorites.main = new Main();
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        currentWeatherFavorites.weather = weatherList;
        currentWeatherFavorites.dt = 1501311011;
        Sys sys = new Sys();
        sys.sunrise = 1501291729;
        sys.sunset = 1501348298;
        currentWeatherFavorites.sys = sys;
    }

    private void initWeatherData() {
        currentWeatherFavorites.setCity("Tambov");
        currentWeatherFavorites.setTemperature(301.998f);
        currentWeatherFavorites.setWeatherType(WeatherType.SUN);
        currentWeatherFavorites.setTimestamp(1501311011000L);
        currentWeatherFavorites.setSunriseTimestamp(1501291729000L);
        currentWeatherFavorites.setSunsetTimestamp(1501348298000L);
    }
}
