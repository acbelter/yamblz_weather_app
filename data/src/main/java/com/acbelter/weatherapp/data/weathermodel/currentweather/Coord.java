package com.acbelter.weatherapp.data.weathermodel.currentweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coord {
    @SerializedName("lon")
    @Expose
    public double lon;
    @SerializedName("lat")
    @Expose
    public double lat;

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }
}
