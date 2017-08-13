package com.acbelter.weatherapp.data.weathermodel.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    @Expose
    private int all;

    public int getAll() {
        return all;
    }

}
