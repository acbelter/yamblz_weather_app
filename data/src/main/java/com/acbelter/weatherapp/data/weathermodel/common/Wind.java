package com.acbelter.weatherapp.data.weathermodel.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    @Expose
    private double speed;
    @SerializedName("deg")
    @Expose
    private double deg;

    /**
     * @return The speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @return The deg
     */
    public double getDeg() {
        return deg;
    }
}
