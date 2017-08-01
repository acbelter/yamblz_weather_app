package com.acbelter.weatherapp.data.netmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("speed")
    @Expose
    public float speed;
    @SerializedName("deg")
    @Expose
    public int degrees;
    @SerializedName("dust")
    @Expose
    public int dust;
}
