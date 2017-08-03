package com.acbelter.weatherapp.domain.model.weather;

import com.acbelter.weatherapp.domain.model.city.CityData;

public class WeatherParams {

    private CityData cityData;

    public CityData getCityData() {
        return cityData;
    }

    public void setCityData(CityData cityData) {
        this.cityData = cityData;
    }

    public WeatherParams(CityData cityData) {

        this.cityData = cityData;
    }
}
