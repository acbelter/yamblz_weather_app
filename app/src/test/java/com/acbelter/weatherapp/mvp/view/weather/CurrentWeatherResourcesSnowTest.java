package com.acbelter.weatherapp.mvp.view.weather;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.WeatherType;
import com.acbelter.weatherapp.mvp.view.weather.common.ResourceUtil;
import com.acbelter.weatherapp.mvp.view.weather.resources.CurrentWeatherRes;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.CELSIUS;
import static junit.framework.Assert.assertEquals;

public class CurrentWeatherResourcesSnowTest {

    private CurrentWeatherFavorites currentWeatherFavorites;
    private ResourceUtil resourceUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        CityData cityData = new CityData.Builder(0, 0, 0L).build();
        this.currentWeatherFavorites = new CurrentWeatherFavorites.Builder(0, cityData, CELSIUS).build();
        this.currentWeatherFavorites.setWeatherType(WeatherType.SNOW);
        this.resourceUtil = new ResourceUtil(currentWeatherFavorites);
    }

    @Test
    public void testTextColorSnowDayResId() {
        resourceUtil.setDayTimestamp();
        CurrentWeatherRes weatherRes = new CurrentWeatherRes(currentWeatherFavorites);
        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

    @Test
    public void testTextColorSnowNightResId() {
        resourceUtil.setNightTimestamp();
        CurrentWeatherRes weatherRes = new CurrentWeatherRes(currentWeatherFavorites);
        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

    @Test
    public void testWeatherImageSnowDayResId() {
        resourceUtil.setDayTimestamp();
        CurrentWeatherRes weatherRes = new CurrentWeatherRes(currentWeatherFavorites);
        assertEquals(R.drawable.img_snow, weatherRes.getWeatherImageResId());
    }

    @Test
    public void testWeatherImageSnowNightResId() {
        resourceUtil.setNightTimestamp();
        CurrentWeatherRes weatherRes = new CurrentWeatherRes(currentWeatherFavorites);
        assertEquals(R.drawable.img_snow, weatherRes.getWeatherImageResId());
    }

    @Test
    public void testBackgroundColorSnowDayResId() {
        resourceUtil.setDayTimestamp();
        CurrentWeatherRes weatherRes = new CurrentWeatherRes(currentWeatherFavorites);
        assertEquals(R.color.colorBgWeatherSnow, weatherRes.getBackgroundColorResId());
    }

    @Test
    public void testBackgroundColorSnowNightResId() {
        resourceUtil.setNightTimestamp();
        CurrentWeatherRes weatherRes = new CurrentWeatherRes(currentWeatherFavorites);
        assertEquals(R.color.colorBgWeatherNight, weatherRes.getBackgroundColorResId());
    }
}