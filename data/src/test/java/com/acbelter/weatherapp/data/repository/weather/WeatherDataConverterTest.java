package com.acbelter.weatherapp.data.repository.weather;

import com.acbelter.weatherapp.data.weathermodel.CurrentWeather;
import com.acbelter.weatherapp.data.weathermodel.Main;
import com.acbelter.weatherapp.data.weathermodel.Sys;
import com.acbelter.weatherapp.data.weathermodel.Weather;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
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
public class WeatherDataConverterTest {

    private CurrentWeather currentWeather;
    private WeatherData weatherData;

    @Before
    public void setUp() {
        currentWeather = new CurrentWeather();
        weatherData = new WeatherData();
    }

    @Test
    public void testCodeError() {
        currentWeather.code = 201;
        assertNull(WeatherDataConverter.fromNetworkData(currentWeather));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWeatherConverterToNull() {
        assertNull(WeatherDataConverter.fromNetworkData(null));
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

        WeatherData testWeatherData = WeatherDataConverter.fromNetworkData(currentWeather);
        assertEquals(testWeatherData.getCity(), weatherData.getCity());
    }

    private void initNetworkWeather() {
        currentWeather.code = 200;
        currentWeather.name = "Tambov";
        Weather weather = new Weather();
        weather.main = "Clear";
        currentWeather.main = new Main();
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        currentWeather.weather = weatherList;
        currentWeather.dt = 1501311011;
        Sys sys = new Sys();
        sys.sunrise = 1501291729;
        sys.sunset = 1501348298;
        currentWeather.sys = sys;
    }

    private void initWeatherData() {
        weatherData.setCity("Tambov");
        weatherData.setTemperatureK(301.998f);
        weatherData.setWeatherType(WeatherType.SUN);
        weatherData.setTimestamp(1501311011000L);
        weatherData.setSunriseTimestamp(1501291729000L);
        weatherData.setSunsetTimestamp(1501348298000L);
    }
}
