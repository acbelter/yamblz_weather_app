package com.acbelter.weatherapp.ui.weather;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.WeatherType;
import com.acbelter.weatherapp.mvp.view.weather.WeatherRes;
import com.acbelter.weatherapp.ui.weather.common.ResourceUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import xyz.matteobattilana.library.Common.Constants;

import static junit.framework.Assert.assertEquals;

public class WeatherResourcesCloudsTest {

    private CurrentWeatherFavorites currentWeatherFavorites;
    private ResourceUtil resourceUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.currentWeatherFavorites = new CurrentWeatherFavorites();
        this.currentWeatherFavorites.setWeatherType(WeatherType.CLOUDS);
        this.resourceUtil = new ResourceUtil(currentWeatherFavorites);
    }

    @Test
    public void testTextColorCloudsDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
        assertEquals(R.color.colorTextWeatherDark, weatherRes.getTextColorResId());
    }

    @Test
    public void testTextColorCloudsNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

    @Test
    public void testWeatherImageCloudsDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
        assertEquals(R.drawable.img_clouds, weatherRes.getWeatherImageResId());
    }

    @Test
    public void testWeatherImageCloudsNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
        assertEquals(R.drawable.img_clouds_night, weatherRes.getWeatherImageResId());
    }

    @Test
    public void testBackgroundColorCloudsDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
        assertEquals(R.color.colorBgWeatherClouds, weatherRes.getBackgroundColorResId());
    }

    @Test
    public void testBackgroundColorCloudsNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
        assertEquals(R.color.colorBgWeatherNight, weatherRes.getBackgroundColorResId());
    }

    @Test
    public void testStatusCloudsDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
        assertEquals(Constants.weatherStatus.SUN, weatherRes.getWeatherStatus());
    }

    @Test
    public void testStatusCloudsNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
        assertEquals(Constants.weatherStatus.SUN, weatherRes.getWeatherStatus());
    }
}
