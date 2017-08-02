package com.acbelter.weatherapp.domain.model.weather;

public class WeatherParams {
    private String cityName;

    public WeatherParams(String city) {
        cityName = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCity(String city) {
        cityName = city;
    }

}
