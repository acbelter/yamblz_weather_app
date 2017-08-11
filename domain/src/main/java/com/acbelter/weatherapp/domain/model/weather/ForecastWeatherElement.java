package com.acbelter.weatherapp.domain.model.weather;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

import static com.acbelter.weatherapp.domain.model.weather.WeatherType.SUN;

public class ForecastWeatherElement {

    @Nullable
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
    private double pressure;
    @NonNull
    private int humidity;
    @Nullable
    private String description;
    @NonNull
    private double windSpeed;

    public static class Builder {
        //Requered params

        private final String date;
        private final double maxTemp;
        private final double minTemp;
        private final TemperatureMetric temperatureMetric;

        //Optional params
        private WeatherType weatherType = SUN;
        private double pressure = 0;
        private int humidity = 0;
        private String description = "";
        private double windSpeed = 0.0;

        public Builder(String date, double minTemp, double maxTemp, TemperatureMetric temperatureMetric) {
            this.date = date;
            this.maxTemp = maxTemp;
            this.minTemp = minTemp;
            this.temperatureMetric = temperatureMetric;
        }

        public Builder weatherType(WeatherType val) {
            this.weatherType = val;
            return this;
        }

        public Builder pressure(double val) {
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

        public Builder windSpeed(double val) {
            this.windSpeed = val;
            return this;
        }

        public ForecastWeatherElement build() {
            return new ForecastWeatherElement(this);
        }
    }

    private ForecastWeatherElement(Builder builder) {
        date = builder.date;
        maxTemp = builder.maxTemp;
        minTemp = builder.minTemp;
        temperatureMetric = builder.temperatureMetric;
        weatherType = builder.weatherType;
        pressure = builder.pressure;
        humidity = builder.humidity;
        description = builder.description;
        windSpeed = builder.windSpeed;
    }

    @Nullable
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
    public double getPressure() {
        return pressure;
    }

    @NonNull
    public int getHumidity() {
        return humidity;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @NonNull
    public double getWindSpeed() {
        return windSpeed;
    }

    @NonNull
    public TemperatureMetric getTemperatureMetric() {
        return temperatureMetric;
    }

    public void setTemperatureMetric(@NonNull TemperatureMetric temperatureMetric) {
        this.temperatureMetric = temperatureMetric;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ForecastWeatherElement data = (ForecastWeatherElement) o;
        if (date != null)
        return (date.equals(data.date))
                && (Double.compare(maxTemp, data.maxTemp) == 0)
                && (Double.compare(minTemp, data.minTemp) == 0)
                && (temperatureMetric == data.temperatureMetric)
                && (weatherType == data.weatherType)
                && (pressure == data.pressure)
                && (humidity == data.humidity)
                && (description.equals(data.description))
                && (windSpeed == data.windSpeed);
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (date == null ? 0 : date.hashCode());
        result = 31 * result + Double.valueOf(minTemp).hashCode();
        result = 31 * result + Double.valueOf(maxTemp).hashCode();
        result = 31 * result + temperatureMetric.hashCode();
        result = 31 * result + weatherType.hashCode();
        result = 31 * result + Double.valueOf(pressure).hashCode();
        result = 31 * result + humidity;
        result = 31 * result + (description == null ? 0 : description.hashCode());
        result = 31 * result + Double.valueOf(windSpeed).hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "( date=" + date +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                ", temperatureMetric =" + temperatureMetric +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", description=" + description +
                ", windSpeed=" + windSpeed +
                ")";
    }
}