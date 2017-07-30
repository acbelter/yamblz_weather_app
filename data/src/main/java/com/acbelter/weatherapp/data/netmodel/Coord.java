package com.acbelter.weatherapp.data.netmodel;

import android.support.annotation.VisibleForTesting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coord {
    @SerializedName("lon")
    @Expose
    public float lon;
    @SerializedName("lat")
    @Expose
    public float lat;

    @VisibleForTesting
    public void setLat(float lat) {
        this.lat = lat;
    }

    @VisibleForTesting
    public void setLon(float lon) {
        this.lon = lon;
    }
}
