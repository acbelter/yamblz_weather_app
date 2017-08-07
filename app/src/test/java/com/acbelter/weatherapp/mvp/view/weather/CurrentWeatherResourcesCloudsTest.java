package com.acbelter.weatherapp.ui.weather;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.WeatherType;
import com.acbelter.weatherapp.mvp.view.weather.CurrentWeatherRes;
import com.acbelter.weatherapp.ui.weather.common.ResourceUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import xyz.matteobattilana.library.Common.Constants;

import static junit.framework.Assert.assertEquals;

public class CurrentWeatherResourcesCloudsTest {

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
        CurrentWeatherRes currentWeatherRes = new CurrentWeatherRes(currentWeatherFavorites);
        assertEquals(R.color.colorTextWeatherDark, currentWeatherRes.getTextColorResId());
    }

    @Test
    public void testTextColorCloudsNightResId() {
        resourceUtil.setNightTimestamp();
        CurrentWeatherRes currentWeatherRes = new CurrentWeatherRes(currentWeatherFavorites);
        assertEquals(R.color.colorTextWeatherLight, currentWeatherRes.getTextColorResId());
    }

    @Test
    public void testWeatherImageCloudsDayResId() {
        resourceUtil.setDayTimestamp();
        CurrentWeatherRes currentWeatherRes = new CurrentWeatherRes(currentWeatherFavorites);
        assertEquals(R.drawable.img_clouds, currentWeatherRes.getWeatherImageResId());
    }

    @Test
    public void testWeatherImageCloudsNightResId() {
        resourceUtil.setNightTimestamp();
        CurrentWeatherRes currentWeatherRes = new CurrentWeatherRes(currentWeatherFavorites);
        assertEquals(R.drawable.img_clouds_night, currentWeatherRes.getWeatherImageResId());
    }

    @Test
    public void testBackgroundColorCloudsDayResId() {
        resourceUtil.setDayTimestamp();
        CurrentWeatherRes currentWeatherRes = new CurrentWeatherRes(currentWeatherFavorites);
        assertEquals(R.color.colorBgWeatherClouds, currentWeatherRes.getBackgroundColorResId());
    }

    @Test
    public void testBackgroundColorCloudsNightResId() {
        resourceUtil.setNightTimestamp();
        CurrentWeatherRes currentWeatherRes = new CurrentWeatherRes(currentWeatherFavorites);
        assertEquals(R.color.colorBgWeatherNight, currentWeatherRes.getBackgroundColorResId());
    }

    @Test
    public void testStatusCloudsDayResId() {
        resourceUtil.setDayTimestamp();
        CurrentWeatherRes currentWeatherRes = new CurrentWeatherRes(currentWeatherFavorites);
        assertEquals(Constants.weatherStatus.SUN, currentWeatherRes.getWeatherStatus());
    }

    @Test
    public void testStatusCloudsNightResId() {
        resourceUtil.setNightTimestamp();
        CurrentWeatherRes currentWeatherRes = new CurrentWeatherRes(currentWeatherFavorites);
        assertEquals(Constants.weatherStatus.SUN, currentWeatherRes.getWeatherStatus());
    }
}
