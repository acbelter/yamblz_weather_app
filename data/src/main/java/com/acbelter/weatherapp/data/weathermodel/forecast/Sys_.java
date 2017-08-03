package com.acbelter.weatherapp.data.weathermodel.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys_ {

    @SerializedName("pod")
    @Expose
    private String pod;

    /**
     * @return The pod
     */
    public String getPod() {
        return pod;
    }

}
