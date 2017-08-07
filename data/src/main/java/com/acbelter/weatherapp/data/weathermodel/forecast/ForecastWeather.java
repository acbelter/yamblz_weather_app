package com.acbelter.weatherapp.data.weathermodel.forecast;

import com.acbelter.weatherapp.data.weathermodel.common.City;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ForecastWeather {

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
    private List<ForecastElement> forecastElement = new ArrayList<>();

    public City getCity() {
        return city;
    }

    public String getCod() {
        return cod;
    }

    public double getMessage() {
        return message;
    }

    public int getCnt() {
        return cnt;
    }

    public List<ForecastElement> getForecastElement() {
        return forecastElement;
    }
}
