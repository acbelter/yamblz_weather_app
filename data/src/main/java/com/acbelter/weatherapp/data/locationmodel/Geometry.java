package com.acbelter.weatherapp.data.locationmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geometry {

    @SerializedName("location")
    @Expose
    private Location_ location;
    @SerializedName("viewport")
    @Expose
    private Viewport viewport;

    public Location_ getLocation() {
        return location;
    }

    public Viewport getViewport() {
        return viewport;
    }
}
