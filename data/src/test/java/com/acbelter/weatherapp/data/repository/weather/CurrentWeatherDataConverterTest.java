package com.acbelter.weatherapp.data.repository.weather;

import com.acbelter.weatherapp.data.weathermodel.currentweather.Main;
import com.acbelter.weatherapp.data.weathermodel.currentweather.Sys;
import com.acbelter.weatherapp.data.weathermodel.currentweather.Weather;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherData;
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
public class CurrentWeatherDataConverterTest {

    private com.acbelter.weatherapp.data.weathermodel.currentweather.CurrentWeather currentWeather;
    private CurrentWeatherData currentWeatherData;

    @Before
    public void setUp() {
        currentWeatherData = new com.acbelter.weatherapp.data.weathermodel.currentweather.CurrentWeather();
        currentWeatherData = new CurrentWeatherData();
    }

    @Test
    public void testCodeError() {
        currentWeatherData.code = 201;
        assertNull(WeatherDataConverter.currentWeatherFromNetworkData(currentWeatherData));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWeatherConverterToNull() {
        assertNull(WeatherDataConverter.currentWeatherFromNetworkData(null));
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

        CurrentWeatherData testCurrentWeatherData = WeatherDataConverter.currentWeatherFromNetworkData(currentWeatherData);
        assertEquals(testCurrentWeatherData.getCity(), currentWeatherData.getCity());
    }

    private void initNetworkWeather() {
        currentWeatherData.code = 200;
        currentWeatherData.name = "Tambov";
        Weather weather = new Weather();
        weather.main = "Clear";
        currentWeatherData.main = new Main();
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        currentWeatherData.weather = weatherList;
        currentWeatherData.dt = 1501311011;
        Sys sys = new Sys();
        sys.sunrise = 1501291729;
        sys.sunset = 1501348298;
        currentWeatherData.sys = sys;
    }

    private void initWeatherData() {
        currentWeatherData.setCity("Tambov");
        currentWeatherData.setTemperature(301.998f);
        currentWeatherData.setWeatherType(WeatherType.SUN);
        currentWeatherData.setTimestamp(1501311011000L);
        currentWeatherData.setSunriseTimestamp(1501291729000L);
        currentWeatherData.setSunsetTimestamp(1501348298000L);
    }
}
