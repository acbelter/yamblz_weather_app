package com.acbelter.weatherapp.domain.model.weather;

import java.util.List;

public class WeatherForecast {

    private List<WeatherData> weatherForecast;

    public List<WeatherData> getWeatherForecast() {
        return weatherForecast;
    }

    public void setWeatherForecast(List<WeatherData> weatherForecast) {
        this.weatherForecast = weatherForecast;
    }
}
