package com.acbelter.weatherapp.domain.model.settings;

import android.support.annotation.NonNull;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

public class SettingsData {

    @NonNull
    private final TemperatureMetric metric;
    @NonNull
    private final long updateWeatherInterval;
    @NonNull
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

    private SettingsData(Builder builder) {
        metric = builder.metric;
        updateWeatherInterval = builder.updateWeatherInterval;
        cityData = builder.cityData;
    }

    @NonNull
    public TemperatureMetric getMetric() {
        return this.metric;
    }

    @NonNull
    public long getUpdateWeatherInterval() {
        return this.updateWeatherInterval;
    }

    @NonNull
    public CityData getSelectedCity() {
        return this.cityData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SettingsData))
            return false;
        SettingsData settingsData = (SettingsData) o;
        return (metric == settingsData.getMetric())
                && (updateWeatherInterval == settingsData.updateWeatherInterval)
                && (cityData.equals(settingsData.cityData));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + metric.hashCode();
        result = 31 * result + Long.valueOf(updateWeatherInterval).hashCode();
        result = 31 * result + cityData.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "(metric = " + metric +
                ", updateWeatherInterval=" + updateWeatherInterval +
                ", cityData=" + cityData +
                ")";
    }
}
