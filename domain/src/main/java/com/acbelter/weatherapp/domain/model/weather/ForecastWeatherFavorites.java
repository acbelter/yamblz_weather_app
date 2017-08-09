package com.acbelter.weatherapp.domain.model.weather;

import android.support.annotation.NonNull;

import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

public class ForecastWeatherFavorites {

    @NonNull
    private String date;
    @NonNull
    private double maxTemp;
    @NonNull
    private double minTemp;
    @NonNull
    private TemperatureMetric temperatureMetric;
    @NonNull
    private WeatherType weatherType;

    public static class Builder {
        //Requered params

        private final String date;
        private final double highTemperature;
        private final double lowTemperature;
        private final TemperatureMetric temperatureMetric;

        //Optional params
        private WeatherType weatherType;

        public Builder(String date, double highTemperature, double lowTemperature, TemperatureMetric temperatureMetric) {
            this.date = date;
            this.highTemperature = highTemperature;
            this.lowTemperature = lowTemperature;
            this.temperatureMetric = temperatureMetric;
        }

        public Builder weatherType(WeatherType val) {
            this.weatherType = val;
            return this;
        }

        public ForecastWeatherFavorites build() {
            return new ForecastWeatherFavorites(this);
        }
    }

    private ForecastWeatherFavorites(Builder builder) {
        date = builder.date;
        maxTemp = builder.highTemperature;
        minTemp = builder.lowTemperature;
        temperatureMetric = builder.temperatureMetric;
        weatherType = builder.weatherType;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    @NonNull
    public double getMaxTemp() {
        return maxTemp;
    }

    @NonNull
    public double getMinTemp() {
        return minTemp;
    }

    @NonNull
    public WeatherType getWeatherType() {
        return weatherType;
    }

    @NonNull
    public TemperatureMetric getTemperatureMetric() {
        return temperatureMetric;
    }

    public void setTemperatureMetric(@NonNull TemperatureMetric temperatureMetric) {
        this.temperatureMetric = temperatureMetric;
    }
}