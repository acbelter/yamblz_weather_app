package com.acbelter.weatherapp.data.weathermodel.forecast;

import com.acbelter.weatherapp.data.weathermodel.currentweather.Clouds;
import com.acbelter.weatherapp.data.weathermodel.currentweather.Main;
import com.acbelter.weatherapp.data.weathermodel.currentweather.Rain;
import com.acbelter.weatherapp.data.weathermodel.currentweather.Weather;
import com.acbelter.weatherapp.data.weathermodel.currentweather.Wind;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherForecastElement {

    @SerializedName("dt")
    @Expose
    private int dt;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = new ArrayList<>();
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("sys")
    @Expose
    private Sys_ sys;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;
    @SerializedName("rain")
    @Expose
    private Rain rain;

    /**
     * @return The dt
     */
    public int getDt() {
        return dt;
    }

    /**
     * @return The main
     */
    public Main getMain() {
        return main;
    }

    /**
     * @return The weather
     */
    public java.util.List<Weather> getWeather() {
        return weather;
    }

    /**
     * @return The clouds
     */
    public Clouds getClouds() {
        return clouds;
    }

    /**
     * @return The wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * @return The sys
     */
    public Sys_ getSys() {
        return sys;
    }

    /**
     * @return The dtTxt
     */
    public String getDtTxt() {
        return dtTxt;
    }

    /**
     * @return The rain
     */
    public Rain getRain() {
        return rain;
    }
}
