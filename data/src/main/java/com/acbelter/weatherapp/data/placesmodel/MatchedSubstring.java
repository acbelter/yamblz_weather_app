package com.acbelter.weatherapp.data.placesmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchedSubstring {

    @SerializedName("length")
    @Expose
    private int length;
    @SerializedName("offset")
    @Expose
    private int offset;

    public int getLength() {
        return length;
    }

    public int getOffset() {
        return offset;
    }
}
