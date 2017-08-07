package com.acbelter.weatherapp.data.weathermodel.forecast;

import com.acbelter.weatherapp.data.weathermodel.common.Weather;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ForecastElement {

    @SerializedName("dt")
    @Expose
    private int dt;
    @SerializedName("temp")
    @Expose
    private Temp temp;
    @SerializedName("pressure")
    @Expose
    private double pressure;
    @SerializedName("humidity")
    @Expose
    private int humidity;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = new ArrayList<>();
    @SerializedName("speed")
    @Expose
    private double speed;
    @SerializedName("deg")
    @Expose
    private int deg;
    @SerializedName("clouds")
    @Expose
    private int clouds;
    @SerializedName("rain")
    @Expose
    private double rain;

    public int getDt() {
        return dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public double getSpeed() {
        return speed;
    }

    public int getDeg() {
        return deg;
    }

    public int getClouds() {
        return clouds;
    }

    public double getRain() {
        return rain;
    }
}
