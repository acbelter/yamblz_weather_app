
package com.acbelter.weatherapp.data.netmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coord {
    @SerializedName("lon")
    @Expose
    public float lon;
    @SerializedName("lat")
    @Expose
    public float lat;
}
