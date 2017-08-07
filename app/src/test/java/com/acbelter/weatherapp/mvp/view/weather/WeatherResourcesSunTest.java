package com.acbelter.weatherapp.ui.weather;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.WeatherType;
import com.acbelter.weatherapp.ui.weather.common.ResourceUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import xyz.matteobattilana.library.Common.Constants;

public class WeatherResourcesSunTest {

    private CurrentWeatherFavorites currentWeatherFavorites;
    private ResourceUtil resourceUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.currentWeatherFavorites = new CurrentWeatherFavorites();
        this.currentWeatherFavorites.setWeatherType(WeatherType.SUN);
        this.resourceUtil = new ResourceUtil(currentWeatherFavorites);
    }

    @Test
    public void testTextColorSunDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

    @Test
    public void testTextColorSunNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

    @Test
    public void testWeatherImageSunDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
        assertEquals(R.drawable.img_sun, weatherRes.getWeatherImageResId());
    }

    @Test
    public void testWeatherImageSunNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
        assertEquals(R.drawable.img_night, weatherRes.getWeatherImageResId());
    }

    @Test
    public void testBackgroundColorSunDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
        assertEquals(R.color.colorBgWeatherSun, weatherRes.getBackgroundColorResId());
    }

    @Test
    public void testBackgroundColorSunNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
        assertEquals(R.color.colorBgWeatherNight, weatherRes.getBackgroundColorResId());
    }

    @Test
    public void testStatusSunDayResId() {
        resourceUtil.setDayTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
        assertEquals(Constants.weatherStatus.SUN, weatherRes.getWeatherStatus());
    }

    @Test
    public void testStatusSunNightResId() {
        resourceUtil.setNightTimestamp();
        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
        assertEquals(Constants.weatherStatus.SUN, weatherRes.getWeatherStatus());
    }
}
