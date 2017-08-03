package com.acbelter.weatherapp.data.weathermodel.forecast;

import com.acbelter.weatherapp.data.weathermodel.currentweather.City;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ExtendedWeather {

    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private double message;
    @SerializedName("cnt")
    @Expose
    private int cnt;
    @SerializedName("list")
    @Expose
    private List<WeatherForecastElement> weatherForecastElement = new ArrayList<>();

    /**
     * @return The city
     */
    public City getCity() {
        return city;
    }

    /**
     * @return The cod
     */
    public String getCod() {
        return cod;
    }

    /**
     * @return The message
     */
    public double getMessage() {
        return message;
    }

    /**
     * @return The cnt
     */
    public int getCnt() {
        return cnt;
    }

    /**
     * @return The list
     */
    public List<WeatherForecastElement> getList() {
        return weatherForecastElement;
    }

}
