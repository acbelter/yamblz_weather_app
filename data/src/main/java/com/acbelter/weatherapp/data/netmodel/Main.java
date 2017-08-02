package com.acbelter.weatherapp.data.netmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    @Expose
    private double temp;
    @SerializedName("humidity")
    @Expose
    private int humidity;
    @SerializedName("pressure")
    @Expose
    private double pressure;
    @SerializedName("temp_min")
    @Expose
    private double tempMin;
    @SerializedName("temp_max")
    @Expose
    private double tempMax;

    public double getTemp() {
        return temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }
}
