package com.acbelter.weatherapp.data.dbmodel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(indices = {@Index(value = {"coordinates"},
        unique = true)})
public class DatabaseWeatherData {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String coordinates;
    @NonNull
    private String cityShortName;
    @NonNull
    private String current;
    @NonNull
    private String forecast;
    @NonNull
    private String timestamp;

    @NonNull
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(@NonNull String timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public
    @NonNull
    String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(@NonNull String coordinates) {
        this.coordinates = coordinates;
    }

    public
    @NonNull
    String getCityShortName() {
        return cityShortName;
    }

    public void setCityShortName(@NonNull String cityShortName) {
        this.cityShortName = cityShortName;
    }

    public
    @NonNull
    String getCurrent() {
        return current;
    }

    public void setCurrent(@NonNull String current) {
        this.current = current;
    }

    public
    @NonNull
    String getForecast() {
        return forecast;
    }

    public void setForecast(@NonNull String forecast) {
        this.forecast = forecast;
    }
}
