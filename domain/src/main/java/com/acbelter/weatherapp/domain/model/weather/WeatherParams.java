package com.acbelter.weatherapp.domain.model.weather;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

public class WeatherParams {

    private CityData cityData;
    private TemperatureMetric metric;

    public WeatherParams(CityData cityData, TemperatureMetric metric) {

        this.cityData = cityData;
        this.metric = metric;

    }

    public TemperatureMetric getMetric() {
        return metric;
    }

    public void setMetric(TemperatureMetric metric) {
        this.metric = metric;
    }

    public CityData getCityData() {
        return cityData;
    }

    public void setCityData(CityData cityData) {
        this.cityData = cityData;
    }
}
