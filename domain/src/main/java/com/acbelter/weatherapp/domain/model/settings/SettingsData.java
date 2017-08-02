package com.acbelter.weatherapp.domain.model.settings;

import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

public class SettingsData {

    private final TemperatureMetric metric;
    private final long updateWeatherInterval;
    private final String cityName;

    public static class Builder {
        //Requered params
        private final TemperatureMetric metric;
        private final long updateWeatherInterval;

        //Optional params
        private String cityName;

        public Builder(TemperatureMetric metric, long updateWeatherInterval) {
            this.metric = metric;
            this.updateWeatherInterval = updateWeatherInterval;
        }

        public Builder cityName(String val) {
            cityName = val;
            return this;
        }

        public SettingsData build() {
            return new SettingsData(this);
        }
    }

    public SettingsData(Builder builder) {
        metric = builder.metric;
        updateWeatherInterval = builder.updateWeatherInterval;
        cityName = builder.cityName;
    }

    public TemperatureMetric getMetric() {
        return this.metric;
    }

    public long getUpdateWeatherInterval() {
        return this.updateWeatherInterval;
    }

    public String getSelectedCityId() {
        return this.cityName;
    }
}
