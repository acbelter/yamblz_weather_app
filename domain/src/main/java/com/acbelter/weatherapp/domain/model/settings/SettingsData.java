package com.acbelter.weatherapp.domain.model.settings;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

public class SettingsData {

    private final TemperatureMetric metric;
    private final long updateWeatherInterval;
    private final CityData cityData;

    public static class Builder {
        //Requered params
        private final TemperatureMetric metric;
        private final long updateWeatherInterval;

        //Optional params
        private CityData cityData;

        public Builder(TemperatureMetric metric, long updateWeatherInterval) {
            this.metric = metric;
            this.updateWeatherInterval = updateWeatherInterval;
        }

        public Builder cityData(CityData val) {
            cityData = val;
            return this;
        }

        public SettingsData build() {
            return new SettingsData(this);
        }
    }

    public SettingsData(Builder builder) {
        metric = builder.metric;
        updateWeatherInterval = builder.updateWeatherInterval;
        cityData = builder.cityData;
    }

    public TemperatureMetric getMetric() {
        return this.metric;
    }

    public long getUpdateWeatherInterval() {
        return this.updateWeatherInterval;
    }

    public CityData getSelectedCityName() {
        return this.cityData;
    }
}
