package com.acbelter.weatherapp.domain.model.weather;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

import static com.acbelter.weatherapp.domain.model.weather.WeatherType.SUN;

public class CurrentWeatherFavorites {

    @NonNull
    private double temperature;
    @NonNull
    private CityData cityData;
    @NonNull
    private TemperatureMetric temperatureMetric;
    @NonNull
    private WeatherType weatherType;
    @NonNull
    private long timestamp;
    @NonNull
    private long sunriseTimestamp;
    @NonNull
    private long sunsetTimestamp;
    @NonNull
    private double pressure;
    @NonNull
    private int humidity;
    @Nullable
    private String description;
    @NonNull
    private double windSpeed;
    @NonNull
    private double minTemp;
    @NonNull
    private double maxTemp;

    public static class Builder {
        //Requered params
        private final double temperature;
        private final CityData cityData;
        private final TemperatureMetric temperatureMetric;

        //Optional params
        private WeatherType weatherType = SUN;
        private long timestamp = 0L;
        private long sunriseTimestamp = 0L;
        private long sunsetTimestamp = 0L;
        private double pressure = 0;
        private int humidity = 0;
        private String description = "";
        private double windSpeed = 0;
        private double minTemp = 0f;
        private double maxTemp = 0f;

        public Builder(double temperature, CityData cityData,
                       TemperatureMetric temperatureMetric) {
            this.temperature = temperature;
            this.cityData = cityData;
            this.temperatureMetric = temperatureMetric;
        }

        public Builder weatherType(WeatherType val) {
            this.weatherType = val;
            return this;
        }

        public Builder timestamp(long val) {
            this.timestamp = val;
            return this;
        }

        public Builder sunriseTimestamp(long val) {
            this.sunriseTimestamp = val;
            return this;
        }

        public Builder sunsetTimestamp(long val) {
            this.sunsetTimestamp = val;
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

        public Builder minTemp(double val) {
            this.minTemp = val;
            return this;
        }

        public Builder maxTemp(double val) {
            this.maxTemp = val;
            return this;
        }

        @NonNull
        public CurrentWeatherFavorites build() {
            return new CurrentWeatherFavorites(this);
        }
    }

    public CurrentWeatherFavorites(Builder builder) {
        temperature = builder.temperature;
        cityData = builder.cityData;
        temperatureMetric = builder.temperatureMetric;
        weatherType = builder.weatherType;
        timestamp = builder.timestamp;
        sunriseTimestamp = builder.sunriseTimestamp;
        sunsetTimestamp = builder.sunsetTimestamp;
        pressure = builder.pressure;
        humidity = builder.humidity;
        description = builder.description;
        windSpeed = builder.windSpeed;
        minTemp = builder.minTemp;
        maxTemp = builder.maxTemp;
    }

    public double getTemperature() {
        return temperature;
    }

    @NonNull
    public CityData getCityData() {
        return cityData;
    }

    @NonNull
    public WeatherType getWeatherType() {
        return weatherType;
    }

    @VisibleForTesting
    public void setWeatherType(@NonNull WeatherType weatherType) {
        this.weatherType = weatherType;
    }

    @NonNull
    public TemperatureMetric getTemperatureMetric() {
        return temperatureMetric;
    }

    public void setTemperatureMetric(@NonNull TemperatureMetric temperatureMetric) {
        this.temperatureMetric = temperatureMetric;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Nullable
    @VisibleForTesting
    public void setTimestamp(long time) {
        this.timestamp = time;
    }

    public long getSunriseTimestamp() {
        return sunriseTimestamp;
    }

    @NonNull
    @VisibleForTesting
    public void setSunriseTimestamp(long time) {
        this.sunriseTimestamp = time;
    }

    public long getSunsetTimestamp() {
        return sunsetTimestamp;
    }

    @NonNull
    @VisibleForTesting
    public void setSunsetTimestamp(long time) {
        this.sunsetTimestamp = time;
    }

    public double getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public boolean isDay() {
        return timestamp > sunriseTimestamp && timestamp < sunsetTimestamp;
    }

    public boolean isNight() {
        return !isDay();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CurrentWeatherFavorites data = (CurrentWeatherFavorites) o;
        return (Double.compare(temperature, data.temperature) == 0)
                && (cityData.equals(data.cityData))
                && (temperatureMetric == data.temperatureMetric)
                && (weatherType == data.weatherType)
                && (timestamp == data.timestamp)
                && (sunsetTimestamp == data.sunsetTimestamp)
                && (sunriseTimestamp == data.sunriseTimestamp)
                && (pressure == data.pressure)
                && (humidity == data.humidity)
                && (description.equals(data.description))
                && (windSpeed == data.windSpeed)
                && (Double.compare(minTemp, data.minTemp) == 0)
                && (Double.compare(maxTemp, data.maxTemp) == 0);

    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Double.valueOf(temperature).hashCode();
        result = 31 * result + cityData.hashCode();
        result = 31 * result + temperatureMetric.hashCode();
        result = 31 * result + weatherType.hashCode();
        result = 31 * result + Long.valueOf(timestamp).hashCode();
        result = 31 * result + Long.valueOf(sunriseTimestamp).hashCode();
        result = 31 * result + Long.valueOf(sunsetTimestamp).hashCode();
        result = 31 * result + Double.valueOf(pressure).hashCode();
        result = 31 * result + humidity;
        result = 31 * result + (description == null ? 0 : description.hashCode());
        result = 31 * result + Double.valueOf(windSpeed).hashCode();
        result = 31 * result + Double.valueOf(minTemp).hashCode();
        result = 31 * result + Double.valueOf(maxTemp).hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "( temperature=" + temperature + "K" +
                ", city=" + cityData +
                ", type=" + weatherType +
                ", temperatureMetric =" + temperatureMetric +
                ", timestamp=" + timestamp +
                ", sunrise=" + sunriseTimestamp +
                ", sunset=" + sunsetTimestamp +
                ", day=" + isDay() +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", description=" + description +
                ", windSpeed=" + windSpeed +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                ")";
    }
}
