package com.acbelter.weatherapp.ui.weather;


import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherType;
import com.acbelter.weatherapp.ui.weather.common.ResourceUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import xyz.matteobattilana.library.Common.Constants;

public class WeatherResourcesStormTest {

    private CurrentWeatherData currentWeatherData;
    private ResourceUtil resourceUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.currentWeatherData = new CurrentWeatherData();
        this.currentWeatherData.setWeatherType(WeatherType.STORM);
        this.resourceUtil = new ResourceUtil(currentWeatherData);
    }

    @Test
    public void testTextColorStormDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherData);
        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

    @Test
    public void testTextColorStormNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherData);
        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

    @Test
    public void testWeatherImageStormDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherData);
        assertEquals(R.drawable.img_storm, weatherRes.getWeatherImageResId());
    }

    @Test
    public void testWeatherImageStormNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherData);
        assertEquals(R.drawable.img_storm, weatherRes.getWeatherImageResId());
    }

    @Test
    public void testBackgroundColorStormDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherData);
        assertEquals(R.color.colorBgWeatherStorm, weatherRes.getBackgroundColorResId());
    }

    @Test
    public void testBackgroundColorStormNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherData);
        assertEquals(R.color.colorBgWeatherNight, weatherRes.getBackgroundColorResId());
    }

    @Test
    public void testStatusStormDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherData);
        assertEquals(Constants.weatherStatus.RAIN, weatherRes.getWeatherStatus());
    }

    @Test
    public void testStatusStormNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherData);
        assertEquals(Constants.weatherStatus.RAIN, weatherRes.getWeatherStatus());
    }
}


