package com.acbelter.weatherapp.domain.model.weather;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
    private int pressure;
    @NonNull
    private int humidity;
    @NonNull
    private String description;
    @NonNull
    private int windSpeed;
    @Nullable
    private double minTemp;
    @Nullable
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
        private int pressure = 0;
        private int humidity = 0;
        private String description = "";
        private int windSpeed = 0;
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

        public Builder minTemp(double val) {
            this.minTemp = val;
            return this;
        }

        public Builder maxTemp(double val) {
            this.maxTemp = val;
            return this;
        }

        public
        @NonNull
        CurrentWeatherFavorites build() {
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

    @NonNull
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

    @NonNull
    public TemperatureMetric getTemperatureMetric() {
        return temperatureMetric;
    }

    public void setTemperatureMetric(@NonNull TemperatureMetric temperatureMetric) {
        this.temperatureMetric = temperatureMetric;
    }

    @NonNull
    public long getTimestamp() {
        return timestamp;
    }

    @NonNull
    public long getSunriseTimestamp() {
        return sunriseTimestamp;
    }

    @NonNull
    public long getSunsetTimestamp() {
        return sunsetTimestamp;
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

    @Nullable
    public double getMinTemp() {
        return minTemp;
    }

    @Nullable
    public double getMaxTemp() {
        return maxTemp;
    }

    @NonNull
    public boolean isDay() {
        return timestamp > sunriseTimestamp && timestamp < sunsetTimestamp;
    }

    @NonNull
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
        return cityData.equals(data.cityData);
    }

    @Override
    public int hashCode() {
        return cityData.hashCode();
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
