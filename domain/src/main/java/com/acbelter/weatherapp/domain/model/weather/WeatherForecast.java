package com.acbelter.weatherapp.domain.model.weather;

import java.util.List;

public class WeatherForecast {

    private List<CurrentWeatherData> weatherForecast;

    public List<CurrentWeatherData> getWeatherForecast() {
        return weatherForecast;
    }

    public void setWeatherForecast(List<CurrentWeatherData> weatherForecast) {
        this.weatherForecast = weatherForecast;
    }
}
