package com.acbelter.weatherapp.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

// FIXME Implementation for testing
public class WeatherData implements Parcelable {
    public String date;
    public String temperature;
    public String location;

    public static WeatherData newDebugInstance() {
        WeatherData data = new WeatherData();
        data.date = "13 July 2017";
        data.temperature = "31";
        data.location = "Moscow region";
        return data;
    }

    public WeatherData() {

    }

    protected WeatherData(Parcel in) {
        date = in.readString();
        temperature = in.readString();
        location = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(temperature);
        dest.writeString(location);
    }
}
