package com.acbelter.weatherapp.data.locationmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location_ {

    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lng")
    @Expose
    private double lng;

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

}
