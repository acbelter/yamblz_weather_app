package com.acbelter.weatherapp.data.weathermodel.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    @Expose
    private double temp;
    @SerializedName("temp_min")
    @Expose
    private double tempMin;
    @SerializedName("temp_max")
    @Expose
    private double tempMax;
    @SerializedName("pressure")
    @Expose
    private double pressure;
    @SerializedName("sea_level")
    @Expose
    private double seaLevel;
    @SerializedName("grnd_level")
    @Expose
    private double grndLevel;
    @SerializedName("humidity")
    @Expose
    private int humidity;
    @SerializedName("temp_kf")
    @Expose
    private double tempKf;

    /**
     * @return The temp
     */
    public double getTemp() {
        return temp;
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

    /**
     * @return The pressure
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * @return The seaLevel
     */
    public double getSeaLevel() {
        return seaLevel;
    }

    /**
     * @return The grndLevel
     */
    public double getGrndLevel() {
        return grndLevel;
    }

    /**
     * @return The humidity
     */
    public int getHumidity() {
        return humidity;
    }

    /**
     * @return The tempKf
     */
    public double getTempKf() {
        return tempKf;
    }
}