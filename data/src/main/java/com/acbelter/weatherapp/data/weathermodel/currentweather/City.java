package com.acbelter.weatherapp.data.weathermodel.currentweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("population")
    @Expose
    private int population;
    @SerializedName("sys")
    @Expose
    private Sys sys;

    /**
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The coord
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * @return The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return The population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * @return The sys
     */
    public Sys getSys() {
        return sys;
    }

}
