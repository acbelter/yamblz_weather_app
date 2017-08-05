package com.acbelter.weatherapp.data.weathermodel.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coord {

    @SerializedName("lon")
    @Expose
    private double lon;
    @SerializedName("lat")
    @Expose
    private double lat;

    /**
     * @return The lon
     */
    public double getLon() {
        return lon;
    }

    /**
     * @return The lat
     */
    public double getLat() {
        return lat;
    }

}
