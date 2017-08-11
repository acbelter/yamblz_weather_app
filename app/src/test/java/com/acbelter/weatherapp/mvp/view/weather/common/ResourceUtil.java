package com.acbelter.weatherapp.mvp.view.weather.common;

import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;

import java.util.Calendar;

public class ResourceUtil {

    private CurrentWeatherFavorites currentWeatherFavorites;

    private long timestamp = 0;

    public ResourceUtil(CurrentWeatherFavorites currentWeatherFavorites) {
        this.currentWeatherFavorites = currentWeatherFavorites;

        setDate();
    }

    private void setDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, Calendar.AUGUST);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        timestamp = calendar.getTimeInMillis();
//        currentWeatherFavorites.setTimestamp(timestamp);
    }

    public void setDayTimestamp() {
        long sunriseTimestamp = timestamp - 1;
//        currentWeatherFavorites.setSunriseTimestamp(sunriseTimestamp);
//        long sunsetTimestamp = timestamp + 1;
//        currentWeatherFavorites.setSunsetTimestamp(sunsetTimestamp);
    }

    public void setNightTimestamp() {
        long sunriseTimestamp = timestamp + 1;
//        currentWeatherFavorites.setSunriseTimestamp(sunriseTimestamp);
//        long sunsetTimestamp = timestamp - 1;
//        currentWeatherFavorites.setSunsetTimestamp(sunsetTimestamp);
    }
}
