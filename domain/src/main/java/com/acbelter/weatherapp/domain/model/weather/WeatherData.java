package com.acbelter.weatherapp.domain.model.weather;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

public class WeatherData {

    private CityData cityData;
    private int temperature;
    private WeatherType weatherType;
    private TemperatureMetric temperatureMetric;

    public CityData getCityData() {
        return cityData;
    }

    public void setCityData(CityData cityData) {
        this.cityData = cityData;
    }

    public TemperatureMetric getTemperatureMetric() {
        return temperatureMetric;
    }

    public void setTemperatureMetric(TemperatureMetric temperatureMetric) {
        this.temperatureMetric = temperatureMetric;
    }

    private long timestamp;
    private long sunriseTimestamp;
    private long sunsetTimestamp;

    public WeatherData() {
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setWeatherType(WeatherType type) {
        weatherType = type;
    }

    public WeatherType getWeatherType() {
        return weatherType;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setSunriseTimestamp(long timestamp) {
        sunriseTimestamp = timestamp;
    }

    public long getSunriseTimestamp() {
        return sunriseTimestamp;
    }

    public void setSunsetTimestamp(long timestamp) {
        sunsetTimestamp = timestamp;
    }

    public long getSunsetTimestamp() {
        return sunsetTimestamp;
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
        WeatherData data = (WeatherData) o;
        return cityData.equals(data.cityData);
    }

    @Override
    public int hashCode() {
        return cityData.hashCode();
    }

    @Override
    public String toString() {
        return "(city=" + cityData +
                ", temperature=" + temperature + "K" +
                ", type=" + weatherType +
                ", timestamp=" + timestamp +
                ", sunrise=" + sunriseTimestamp +
                ", sunset=" + sunsetTimestamp +
                ", day=" + isDay() +
                ")";
    }
}
