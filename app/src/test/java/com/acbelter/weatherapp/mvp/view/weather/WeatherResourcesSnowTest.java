package com.acbelter.weatherapp.ui.weather;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherType;
import com.acbelter.weatherapp.ui.weather.common.ResourceUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import xyz.matteobattilana.library.Common.Constants;

import static org.junit.Assert.assertEquals;

public class WeatherResourcesSnowTest {

    private WeatherData weatherData;
    private ResourceUtil resourceUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.weatherData = new WeatherData();
        this.weatherData.setWeatherType(WeatherType.SNOW);
        this.resourceUtil = new ResourceUtil(weatherData);
    }

    @Test
    public void testTextColorSnowDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

    @Test
    public void testTextColorSnowNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

    @Test
    public void testWeatherImageSnowDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.drawable.img_snow, weatherRes.getWeatherImageResId());
    }

    @Test
    public void testWeatherImageSnowNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.drawable.img_snow, weatherRes.getWeatherImageResId());
    }

    @Test
    public void testBackgroundColorSnowDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.color.colorBgWeatherSnow, weatherRes.getBackgroundColorResId());
    }

    @Test
    public void testBackgroundColorSnowNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.color.colorBgWeatherNight, weatherRes.getBackgroundColorResId());
    }

    @Test
    public void testStatusSnowDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(Constants.weatherStatus.SNOW, weatherRes.getWeatherStatus());
    }

    @Test
    public void testStatusSnowNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(Constants.weatherStatus.SNOW, weatherRes.getWeatherStatus());
    }
}