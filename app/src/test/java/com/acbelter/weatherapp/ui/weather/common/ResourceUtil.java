package com.acbelter.weatherapp.ui.weather.common;

import com.acbelter.weatherapp.domain.model.weather.WeatherData;

import java.util.Calendar;

public class ResourceUtil {

    private WeatherData weatherData;

    private long timestamp = 0;

    public ResourceUtil(WeatherData weatherData) {
        this.weatherData = weatherData;

        setDate();
    }

    private void setDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, Calendar.AUGUST);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        timestamp = calendar.getTimeInMillis();
        weatherData.setTimestamp(timestamp);
    }

    public void setDayTimestamp() {
        long sunriseTimestamp = timestamp - 1;
        weatherData.setSunriseTimestamp(sunriseTimestamp);
        long sunsetTimestamp = timestamp + 1;
        weatherData.setSunsetTimestamp(sunsetTimestamp);
    }

    public void setNightTimestamp() {
        long sunriseTimestamp = timestamp + 1;
        weatherData.setSunriseTimestamp(sunriseTimestamp);
        long sunsetTimestamp = timestamp - 1;
        weatherData.setSunsetTimestamp(sunsetTimestamp);
    }
}
