package com.acbelter.weatherapp.data.placesmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Term {

    @SerializedName("offset")
    @Expose
    private int offset;
    @SerializedName("value")
    @Expose
    private String value;

    public int getOffset() {
        return offset;
    }

    public String getValue() {
        return value;
    }
}
