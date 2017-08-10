package com.acbelter.weatherapp.domain.model.weather;

import android.support.annotation.NonNull;

import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

import static com.acbelter.weatherapp.domain.model.weather.WeatherType.SUN;

public class ForecastWeatherElement {

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
    @NonNull
    private int pressure;
    @NonNull
    private int humidity;
    @NonNull
    private String description;
    @NonNull
    private int windSpeed;

    public static class Builder {
        //Requered params

        private final String date;
        private final double highTemperature;
        private final double lowTemperature;
        private final TemperatureMetric temperatureMetric;

        //Optional params
        private WeatherType weatherType = SUN;
        private int pressure = 0;
        private int humidity = 0;
        private String description = "";
        private int windSpeed = 0;

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

        public Builder pressure(int val) {
            this.pressure = val;
            return this;
        }

        public Builder humidity(int val) {
            this.humidity = val;
            return this;
        }

        public Builder description(String val) {
            this.description = val;
            return this;
        }

        public Builder windSpeed(int val) {
            this.windSpeed = val;
            return this;
        }

        public ForecastWeatherElement build() {
            return new ForecastWeatherElement(this);
        }
    }

    private ForecastWeatherElement(Builder builder) {
        date = builder.date;
        maxTemp = builder.highTemperature;
        minTemp = builder.lowTemperature;
        temperatureMetric = builder.temperatureMetric;
        weatherType = builder.weatherType;
        pressure = builder.pressure;
        humidity = builder.humidity;
        description = builder.description;
        windSpeed = builder.windSpeed;
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
    public int getPressure() {
        return pressure;
    }

    @NonNull
    public int getHumidity() {
        return humidity;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public int getWindSpeed() {
        return windSpeed;
    }

    @NonNull
    public TemperatureMetric getTemperatureMetric() {
        return temperatureMetric;
    }

    public void setTemperatureMetric(@NonNull TemperatureMetric temperatureMetric) {
        this.temperatureMetric = temperatureMetric;
    }
}