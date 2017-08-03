package com.acbelter.weatherapp.data.weathermodel.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("population")
    @Expose
    private int population;

    /**
     * @return The population
     */
    public int getPopulation() {
        return population;
    }

}
