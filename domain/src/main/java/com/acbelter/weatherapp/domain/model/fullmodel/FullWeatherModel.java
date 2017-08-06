package com.acbelter.weatherapp.domain.model.fullmodel;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;

import java.util.List;

public class FullWeatherModel {

    CityData cityData;
    CurrentWeatherData currentWeatherData;
    List<WeatherForecast> forrecast;

    public FullWeatherModel(CityData cityData, CurrentWeatherData currentWeatherData, List<WeatherForecast> forrecast) {
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

    public List<WeatherForecast> getForrecast() {
        return forrecast;
    }

    public void setForrecast(List<WeatherForecast> forrecast) {
        this.forrecast = forrecast;
    }
}
