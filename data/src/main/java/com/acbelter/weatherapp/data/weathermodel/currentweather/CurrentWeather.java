package com.acbelter.weatherapp.data.weathermodel.currentweather;

import com.acbelter.weatherapp.data.weathermodel.common.Clouds;
import com.acbelter.weatherapp.data.weathermodel.common.Coord;
import com.acbelter.weatherapp.data.weathermodel.common.Rain;
import com.acbelter.weatherapp.data.weathermodel.common.Weather;
import com.acbelter.weatherapp.data.weathermodel.common.Wind;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CurrentWeather {

    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = new ArrayList<>();
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("rain")
    @Expose
    private Rain rain;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private int dt;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private int cod;

    /**
     * @return The coord
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * @return The sys
     */
    public Sys getSys() {
        return sys;
    }

    /**
     * @return The weather
     */
    public List<Weather> getWeather() {
        return weather;
    }

    /**
     * @return The main
     */
    public Main getMain() {
        return main;
    }

    /**
     * @return The wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * @return The rain
     */
    public Rain getRain() {
        return rain;
    }

    /**
     * @return The clouds
     */
    public Clouds getClouds() {
        return clouds;
    }

    /**
     * @return The dt
     */
    public int getDt() {
        return dt;
    }

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
     * @return The cod
     */
    public int getCod() {
        return cod;
    }

}
