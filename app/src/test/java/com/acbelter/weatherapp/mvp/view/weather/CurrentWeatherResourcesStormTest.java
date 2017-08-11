package com.acbelter.weatherapp.mvp.view.weather;


import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.mvp.view.weather.common.ResourceUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class CurrentWeatherResourcesStormTest {

    private CurrentWeatherFavorites currentWeatherFavorites;
    private ResourceUtil resourceUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

//        this.currentWeatherFavorites = new CurrentWeatherFavorites();
//        this.currentWeatherFavorites.setWeatherType(WeatherType.STORM);
        this.resourceUtil = new ResourceUtil(currentWeatherFavorites);
    }

    @Test
    public void testTextColorStormDayResId() {
//        resourceUtil.setDayTimestamp();
//        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
//        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
    }

//    @Test
//    public void testTextColorStormNightResId() {
//        resourceUtil.setNightTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(R.color.colorTextWeatherLight, weatherRes.getTextColorResId());
//    }
//
//    @Test
//    public void testWeatherImageStormDayResId() {
//        resourceUtil.setDayTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(R.drawable.img_storm, weatherRes.getWeatherImageResId());
//    }
//
//    @Test
//    public void testWeatherImageStormNightResId() {
//        resourceUtil.setNightTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(R.drawable.img_storm, weatherRes.getWeatherImageResId());
//    }
//
//    @Test
//    public void testBackgroundColorStormDayResId() {
//        resourceUtil.setDayTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(R.color.colorBgWeatherStorm, weatherRes.getBackgroundColorResId());
//    }
//
//    @Test
//    public void testBackgroundColorStormNightResId() {
//        resourceUtil.setNightTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(R.color.colorBgWeatherNight, weatherRes.getBackgroundColorResId());
//    }
//
//    @Test
//    public void testStatusStormDayResId() {
//        resourceUtil.setDayTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(Constants.weatherStatus.RAIN, weatherRes.getWeatherStatus());
//    }
//
//    @Test
//    public void testStatusStormNightResId() {
//        resourceUtil.setNightTimestamp();
////        WeatherRes weatherRes = new WeatherRes(currentWeatherFavorites);
////        assertEquals(Constants.weatherStatus.RAIN, weatherRes.getWeatherStatus());
//    }
}


