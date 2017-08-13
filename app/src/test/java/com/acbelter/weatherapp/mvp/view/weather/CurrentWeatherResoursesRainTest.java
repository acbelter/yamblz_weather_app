package com.acbelter.weatherapp.mvp.view.weather;

import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.mvp.view.weather.common.ResourceUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class CurrentWeatherResoursesRainTest {

    private CurrentWeatherFavorites currentWeatherFavorites;
    private ResourceUtil resourceUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

//        this.currentWeatherFavorites = new CurrentWeatherFavorites();
//        this.currentWeatherFavorites.setWeatherType(WeatherType.RAIN);
        this.resourceUtil = new ResourceUtil(currentWeatherFavorites);
    }

    @Test
    public void testTextColorRainDayResId() {
//        resourceUtil.setDayTimestamp();
//        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
//        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

//    @Test
//    public void testTextColorRainNightResId() {
//        resourceUtil.setNightTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
//    }
//
//    @Test
//    public void testWeatherImageRainDayResId() {
//        resourceUtil.setDayTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(R.drawable.img_rain, weatherRes.getWeatherImageResId());
//    }
//
//    @Test
//    public void testWeatherImageRainNightResId() {
//        resourceUtil.setNightTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(R.drawable.img_rain, weatherRes.getWeatherImageResId());
//    }
//
//    @Test
//    public void testBackgroundColorRainDayResId() {
//        resourceUtil.setDayTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(R.color.colorBgWeatherRain, weatherRes.getBackgroundColorResId());
//    }
//
//    @Test
//    public void testBackgroundColorRainNightResId() {
//        resourceUtil.setNightTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(R.color.colorBgWeatherNight, weatherRes.getBackgroundColorResId());
//    }
//
//    @Test
//    public void testStatusRainDayResId() {
//        resourceUtil.setDayTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(Constants.weatherStatus.RAIN, weatherRes.getWeatherStatus());
//    }
//
//    @Test
//    public void testStatusRainNightResId() {
//        resourceUtil.setNightTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(Constants.weatherStatus.RAIN, weatherRes.getWeatherStatus());
//    }
}


