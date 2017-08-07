package com.acbelter.weatherapp.domain.model.weather;

import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

public class ForecastWeatherFavorites {

    private String date;
    private int maxTemp;
    private int minTemp;
    private TemperatureMetric temperatureMetric;
    private WeatherType weatherType;

    public static class Builder {
        //Requered params

        private final String date;
        private final int highTemperature;
        private final int lowTemperature;
        private final TemperatureMetric temperatureMetric;

        //Optional params
        private WeatherType weatherType;

        public Builder(String date, int highTemperature, int lowTemperature, TemperatureMetric temperatureMetric) {
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

    public ForecastWeatherFavorites(Builder builder) {
        date = builder.date;
        maxTemp = builder.highTemperature;
        minTemp = builder.lowTemperature;
        temperatureMetric = builder.temperatureMetric;
        weatherType = builder.weatherType;
    }

    public String getDate() {
        return date;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public WeatherType getWeatherType() {
        return weatherType;
    }

    public TemperatureMetric getTemperatureMetric() {
        return temperatureMetric;
    }
}
