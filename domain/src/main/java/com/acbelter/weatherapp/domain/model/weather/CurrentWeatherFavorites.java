package com.acbelter.weatherapp.domain.model.weather;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

public class CurrentWeatherFavorites {

    private int temperature;
    private CityData cityData;
    private WeatherType weatherType;
    private TemperatureMetric temperatureMetric;
    private long timestamp;
    private long sunriseTimestamp;
    private long sunsetTimestamp;
    private int pressure;
    private int humidity;
    private String description;
    private int windSpeed;
    private int minTemp;
    private int maxTemp;

    public static class Builder {
        //Requered params
        private final int temperature;
        private final CityData cityData;
        private final WeatherType weatherType;
        private final TemperatureMetric temperatureMetric;

        //Optional params
        private long timestamp;
        private long sunriseTimestamp;
        private long sunsetTimestamp;
        private int pressure;
        private int humidity;
        private String description;
        private int windSpeed;
        private int minTemp;
        private int maxTemp;

        public Builder(int temperature, CityData cityData, WeatherType weatherType,
                       TemperatureMetric temperatureMetric) {
            this.temperature = temperature;
            this.cityData = cityData;
            this.weatherType = weatherType;
            this.temperatureMetric = temperatureMetric;
        }

        public Builder timestamp(long val) {
            timestamp = val;
            return this;
        }

        public Builder sunriseTimestamp(long val) {
            sunriseTimestamp = val;
            return this;
        }

        public Builder sunsetTimestamp(long val) {
            sunsetTimestamp = val;
            return this;
        }

        public Builder pressure(int val) {
            pressure = val;
            return this;
        }

        public Builder humidity(int val) {
            humidity = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder windSpeed(int val) {
            windSpeed = val;
            return this;
        }

        public Builder minTemp(int val) {
            minTemp = val;
            return this;
        }

        public Builder maxTemp(int val) {
            maxTemp = val;
            return this;
        }

        public CurrentWeatherFavorites build() {
            return new CurrentWeatherFavorites(this);
        }
    }

    public CurrentWeatherFavorites(Builder builder) {
        temperature = builder.temperature;
        cityData = builder.cityData;
        weatherType = builder.weatherType;
        temperatureMetric = builder.temperatureMetric;
    }

    public int getTemperature() {
        return temperature;
    }

    public CityData getCityData() {
        return cityData;
    }

    public WeatherType getWeatherType() {
        return weatherType;
    }

    public TemperatureMetric getTemperatureMetric() {
        return temperatureMetric;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getSunriseTimestamp() {
        return sunriseTimestamp;
    }

    public long getSunsetTimestamp() {
        return sunsetTimestamp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getDescription() {
        return description;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public int getMaxTemp() {
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
