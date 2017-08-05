package com.acbelter.weatherapp.data.weathermodel.currentweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("sunrise")
    @Expose
    private int sunrise;
    @SerializedName("sunset")
    @Expose
    private int sunset;

    /**
     * @return The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return The sunrise
     */
    public int getSunrise() {
        return sunrise;
    }

    /**
     * @return The sunset
     */
    public int getSunset() {
        return sunset;
    }
}
