package com.acbelter.weatherapp.domain.model.weather;

import java.util.List;

public class WeatherForecast {

    private List<WeatherForecastElement> weatherForecast;

    public List<WeatherForecastElement> getWeatherForecast() {
        return weatherForecast;
    }

    public void setWeatherForecast(List<WeatherForecastElement> weatherForecast) {
        this.weatherForecast = weatherForecast;
    }
}
