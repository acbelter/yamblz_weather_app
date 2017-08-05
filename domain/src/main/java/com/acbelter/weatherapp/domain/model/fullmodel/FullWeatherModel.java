package com.acbelter.weatherapp.domain.model.fullmodel;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;

public class FullWeatherModel {

    CityData cityData;
    CurrentWeatherData currentWeatherData;
    WeatherForecast forrecast;

    public FullWeatherModel(CityData cityData, CurrentWeatherData currentWeatherData, WeatherForecast forrecast) {
        this.cityData = cityData;
        this.currentWeatherData = currentWeatherData;
        this.forrecast = forrecast;

    }

    public CityData getCityData() {
        return cityData;
    }

    public void setCityData(CityData cityData) {
        this.cityData = cityData;
    }

    public CurrentWeatherData getCurrentWeatherData() {
        return currentWeatherData;
    }

    public void setCurrentWeatherData(CurrentWeatherData currentWeatherData) {
        this.currentWeatherData = currentWeatherData;
    }

    public WeatherForecast getForrecast() {
        return forrecast;
    }

    public void setForrecast(WeatherForecast forrecast) {
        this.forrecast = forrecast;
    }
}
