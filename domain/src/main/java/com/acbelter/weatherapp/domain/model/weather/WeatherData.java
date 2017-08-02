package com.acbelter.weatherapp.domain.model.weather;

public class WeatherData {
    private String mCity;
    private double kelvinTemperature;
    private WeatherType mWeatherType;
    private long mTimestamp;
    private long mSunriseTimestamp;
    private long mSunsetTimestamp;

    public WeatherData() {
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getCity() {
        return mCity;
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
        mWeatherType = type;
    }

    public WeatherType getWeatherType() {
        return mWeatherType;
    }

    public void setTimestamp(long timestamp) {
        mTimestamp = timestamp;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public void setSunriseTimestamp(long timestamp) {
        mSunriseTimestamp = timestamp;
    }

    public long getSunriseTimestamp() {
        return mSunriseTimestamp;
    }

    public void setSunsetTimestamp(long timestamp) {
        mSunsetTimestamp = timestamp;
    }

    public long getSunsetTimestamp() {
        return mSunsetTimestamp;
    }

    public boolean isDay() {
        return mTimestamp > mSunriseTimestamp && mTimestamp < mSunsetTimestamp;
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
        return mCity.equals(data.mCity);
    }

    @Override
    public int hashCode() {
        return mCity.hashCode();
    }

    @Override
    public String toString() {
        return "(city=" + mCity +
                ", temperature=" + kelvinTemperature + "K" +
                ", type=" + mWeatherType +
                ", timestamp=" + mTimestamp +
                ", sunrise=" + mSunriseTimestamp +
                ", sunset=" + mSunsetTimestamp +
                ", day=" + isDay() +
                ")";
    }
}
