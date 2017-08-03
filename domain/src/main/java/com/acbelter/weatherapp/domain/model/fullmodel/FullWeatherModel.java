package com.acbelter.weatherapp.domain.model.fullmodel;

import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;

public class FullWeatherModel {

    String cityName;
    WeatherData weatherData;
    WeatherForecast forrecast;

    public FullWeatherModel(String cityName, WeatherData weatherData, WeatherForecast forrecast) {
        this.cityName = cityName;
        this.weatherData = weatherData;
        this.forrecast = forrecast;

    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public WeatherForecast getForrecast() {
        return forrecast;
    }

    public void setForrecast(WeatherForecast forrecast) {
        this.forrecast = forrecast;
    }
}
