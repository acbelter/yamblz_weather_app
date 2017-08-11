package com.acbelter.weatherapp.mvp.view.weather;

import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.mvp.view.weather.common.ResourceUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class CurrentWeatherResourcesSnowTest {

    private CurrentWeatherFavorites currentWeatherFavorites;
    private ResourceUtil resourceUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

//        this.currentWeatherFavorites = new CurrentWeatherFavorites();
//        this.currentWeatherFavorites.setWeatherType(WeatherType.SNOW);
        this.resourceUtil = new ResourceUtil(currentWeatherFavorites);
    }

    @Test
    public void testTextColorSnowDayResId() {
//        resourceUtil.setDayTimestamp();
//        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
//        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

//    @Test
//    public void testTextColorSnowNightResId() {
//        resourceUtil.setNightTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
//    }
//
//    @Test
//    public void testWeatherImageSnowDayResId() {
//        resourceUtil.setDayTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(R.drawable.img_snow, weatherRes.getWeatherImageResId());
//    }
//
//    @Test
//    public void testWeatherImageSnowNightResId() {
//        resourceUtil.setNightTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(R.drawable.img_snow, weatherRes.getWeatherImageResId());
//    }
//
//    @Test
//    public void testBackgroundColorSnowDayResId() {
//        resourceUtil.setDayTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(R.color.colorBgWeatherSnow, weatherRes.getBackgroundColorResId());
//    }
//
//    @Test
//    public void testBackgroundColorSnowNightResId() {
//        resourceUtil.setNightTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(R.color.colorBgWeatherNight, weatherRes.getBackgroundColorResId());
//    }
//
//    @Test
//    public void testStatusSnowDayResId() {
//        resourceUtil.setDayTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(Constants.weatherStatus.SNOW, weatherRes.getWeatherStatus());
//    }
//
//    @Test
//    public void testStatusSnowNightResId() {
//        resourceUtil.setNightTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(Constants.weatherStatus.SNOW, weatherRes.getWeatherStatus());
//    }
}