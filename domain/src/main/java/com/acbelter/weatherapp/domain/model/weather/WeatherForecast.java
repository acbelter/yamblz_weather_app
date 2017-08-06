package com.acbelter.weatherapp.domain.model.weather;

public class WeatherForecast {

    private String date;
    private int highTemperature;
    private int lowTemperature;

    public WeatherForecast(String date, int highTemperature, int lowTemperature) {

        this.date = date;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHighTemperature() {
        return highTemperature;
    }

    public void setHighTemperature(int highTemperature) {
        this.highTemperature = highTemperature;
    }

    public int getLowTemperature() {
        return lowTemperature;
    }

    public void setLowTemperature(int lowTemperature) {
        this.lowTemperature = lowTemperature;
    }
}
