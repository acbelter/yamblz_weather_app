package com.acbelter.weatherapp.domain.model.weather;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherData implements Parcelable {
    private String mCity;
    private float mKelvinTemperature;
    private WeatherType mWeatherType;
    private long mTimestamp;
    private long mSunriseTimestamp;
    private long mSunsetTimestamp;

    public WeatherData() {
    }

    protected WeatherData(Parcel in) {
        mCity = in.readString();
        mKelvinTemperature = in.readFloat();
        mWeatherType = WeatherType.valueOf(in.readString());
        mTimestamp = in.readLong();
        mSunriseTimestamp = in.readLong();
        mSunsetTimestamp = in.readLong();
    }

    public static final Creator<WeatherData> CREATOR = new Creator<WeatherData>() {
        @Override
        public WeatherData createFromParcel(Parcel in) {
            return new WeatherData(in);
        }

        @Override
        public WeatherData[] newArray(int size) {
            return new WeatherData[size];
        }
    };

    public void setCity(String city) {
        mCity = city;
    }

    public String getCity() {
        return mCity;
    }

    public void setTemperatureK(float temperature) {
        mKelvinTemperature = temperature;
    }

    public float getTemperatureK() {
        return mKelvinTemperature;
    }

    public float getTemperatureC() {
        return mKelvinTemperature - 273.15f;
    }

    public float getTemperatureF() {
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCity);
        dest.writeFloat(mKelvinTemperature);
        dest.writeString(mWeatherType.name());
        dest.writeLong(mTimestamp);
        dest.writeLong(mSunriseTimestamp);
        dest.writeLong(mSunsetTimestamp);
    }

    @Override
    public String toString() {
        return "(city=" + mCity +
                ", temperature=" + mKelvinTemperature + "K" +
                ", type=" + mWeatherType +
                ", timestamp=" + mTimestamp +
                ", sunrise=" + mSunriseTimestamp +
                ", sunset=" + mSunsetTimestamp +
                ", day=" + isDay() +
                ")";
    }
}
