package com.acbelter.weatherapp.domain.model.weather;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

public class WeatherParams {

    @NonNull
    private CityData cityData;
    @Nullable
    private TemperatureMetric metric;

    public WeatherParams(@NonNull CityData cityData, @Nullable TemperatureMetric metric) {
        this.cityData = cityData;
        this.metric = metric;
    }

    public
    @Nullable
    TemperatureMetric getMetric() {
        return metric;
    }

    public void setMetric(@Nullable TemperatureMetric metric) {
        this.metric = metric;
    }

    public
    @NonNull
    CityData getCityData() {
        return cityData;
    }

    public void setCityData(@NonNull CityData cityData) {
        this.cityData = cityData;
    }
}
