package com.acbelter.weatherapp.domain.model.fullmodel;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;

public class FullWeatherModel {

    CityData cityData;
    WeatherData weatherData;
    WeatherForecast forrecast;

    public FullWeatherModel(CityData cityData, WeatherData weatherData, WeatherForecast forrecast) {
        this.cityData = cityData;
        this.weatherData = weatherData;
        this.forrecast = forrecast;

    }

    public CityData getCityData() {
        return cityData;
    }

    public void setCityData(CityData cityData) {
        this.cityData = cityData;
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
