package com.acbelter.weatherapp.data.weathermodel.currentweather;

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

    /**
     * @return The temp
     */
    public double getTemp() {
        return temp;
    }

    /**
     * @return The humidity
     */
    public int getHumidity() {
        return humidity;
    }

    /**
     * @return The pressure
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * @return The tempMin
     */
    public double getTempMin() {
        return tempMin;
    }

    /**
     * @return The tempMax
     */
    public double getTempMax() {
        return tempMax;
    }
}
