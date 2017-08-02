package com.acbelter.weatherapp.domain.model.weather;

public class WeatherData {
    private String cityName;
    private double kelvinTemperature;
    private WeatherType weatherType;
    private long timestamp;
    private long sunriseTimestamp;
    private long sunsetTimestamp;

    public WeatherData() {
    }

    public void setCity(String city) {
        cityName = city;
    }

    public String getCity() {
        return cityName;
    }

    public void setTemperatureK(double temperature) {
        kelvinTemperature = temperature;
    }

    public double getTemperatureK() {
        return kelvinTemperature;
    }

    public double getTemperatureC() {
        return kelvinTemperature - 273.15f;
    }

    public double getTemperatureF() {
        return 1.8f * getTemperatureC() + 32;
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
        return cityName.equals(data.cityName);
    }

    @Override
    public int hashCode() {
        return cityName.hashCode();
    }

    @Override
    public String toString() {
        return "(city=" + cityName +
                ", temperature=" + kelvinTemperature + "K" +
                ", type=" + weatherType +
                ", timestamp=" + timestamp +
                ", sunrise=" + sunriseTimestamp +
                ", sunset=" + sunsetTimestamp +
                ", day=" + isDay() +
                ")";
    }
}
