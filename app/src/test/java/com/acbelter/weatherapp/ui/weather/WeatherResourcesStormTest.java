package com.acbelter.weatherapp.ui.weather;


import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherType;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.acbelter.weatherapp.ui.weather.common.ResourceUtil;
import xyz.matteobattilana.library.Common.Constants;

import static org.junit.Assert.assertEquals;

public class WeatherResourcesStormTest {

    private WeatherData weatherData;
    private ResourceUtil resourceUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.weatherData = new WeatherData();
        this.weatherData.setWeatherType(WeatherType.STORM);
        this.resourceUtil = new ResourceUtil(weatherData);
    }

    @Test
    public void testTextColorStormDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

    @Test
    public void testTextColorStormNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

    @Test
    public void testWeatherImageStormDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.drawable.img_storm, weatherRes.getWeatherImageResId());
    }

    @Test
    public void testWeatherImageStormNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.drawable.img_storm, weatherRes.getWeatherImageResId());
    }

    @Test
    public void testBackgroundColorStormDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.color.colorBgWeatherStorm, weatherRes.getBackgroundColorResId());
    }

    @Test
    public void testBackgroundColorStormNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(R.color.colorBgWeatherNight, weatherRes.getBackgroundColorResId());
    }

    @Test
    public void testStatusStormDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(Constants.weatherStatus.RAIN, weatherRes.getWeatherStatus());
    }

    @Test
    public void testStatusStormNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(weatherData);
        assertEquals(Constants.weatherStatus.RAIN, weatherRes.getWeatherStatus());
    }
}


