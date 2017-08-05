package com.acbelter.weatherapp.ui.weather;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherType;
import com.acbelter.weatherapp.ui.weather.common.ResourceUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import xyz.matteobattilana.library.Common.Constants;

public class WeatherResourcesSnowTest {

    private CurrentWeatherData currentWeatherData;
    private ResourceUtil resourceUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.currentWeatherData = new CurrentWeatherData();
        this.currentWeatherData.setWeatherType(WeatherType.SNOW);
        this.resourceUtil = new ResourceUtil(currentWeatherData);
    }

    @Test
    public void testTextColorSnowDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherData);
        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

    @Test
    public void testTextColorSnowNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherData);
        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

    @Test
    public void testWeatherImageSnowDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherData);
        assertEquals(R.drawable.img_snow, weatherRes.getWeatherImageResId());
    }

    @Test
    public void testWeatherImageSnowNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherData);
        assertEquals(R.drawable.img_snow, weatherRes.getWeatherImageResId());
    }

    @Test
    public void testBackgroundColorSnowDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherData);
        assertEquals(R.color.colorBgWeatherSnow, weatherRes.getBackgroundColorResId());
    }

    @Test
    public void testBackgroundColorSnowNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherData);
        assertEquals(R.color.colorBgWeatherNight, weatherRes.getBackgroundColorResId());
    }

    @Test
    public void testStatusSnowDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherData);
        assertEquals(Constants.weatherStatus.SNOW, weatherRes.getWeatherStatus());
    }

    @Test
    public void testStatusSnowNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherData);
        assertEquals(Constants.weatherStatus.SNOW, weatherRes.getWeatherStatus());
    }
}