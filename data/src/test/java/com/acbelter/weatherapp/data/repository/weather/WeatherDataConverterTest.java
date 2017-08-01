package com.acbelter.weatherapp.data.repository.weather;

import com.acbelter.weatherapp.data.netmodel.Main;
import com.acbelter.weatherapp.data.netmodel.NetworkWeatherData;
import com.acbelter.weatherapp.data.netmodel.Sys;
import com.acbelter.weatherapp.data.netmodel.Weather;
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

    private NetworkWeatherData networkWeatherData;
    private WeatherData weatherData;

    @Before
    public void setUp() {
        networkWeatherData = new NetworkWeatherData();
        weatherData = new WeatherData();
    }

    @Test
    public void testCodeError() {
        networkWeatherData.code = 201;
        assertNull(WeatherDataConverter.fromNetworkData(networkWeatherData));
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

        WeatherData testWeatherData = WeatherDataConverter.fromNetworkData(networkWeatherData);
        assertEquals(testWeatherData.getCity(), weatherData.getCity());
    }

    private void initNetworkWeather() {
        networkWeatherData.code = 200;
        networkWeatherData.name = "Tambov";
        Weather weather = new Weather();
        weather.main = "Clear";
        networkWeatherData.main = new Main();
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        networkWeatherData.weather = weatherList;
        networkWeatherData.dt = 1501311011;
        Sys sys = new Sys();
        sys.sunrise = 1501291729;
        sys.sunset = 1501348298;
        networkWeatherData.sys = sys;
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
