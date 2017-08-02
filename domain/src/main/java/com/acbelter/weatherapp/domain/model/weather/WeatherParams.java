package com.acbelter.weatherapp.domain.model.weather;

public class WeatherParams {
    private String cityName;
    private String lang;

    public WeatherParams(String city) {
        cityName = city;
        lang = "en";
    }

    public String getCityName() {
        return cityName;
    }

    public void setCity(String city) {
        cityName = city;
    }

    public String getLangCode() {
        return lang;
    }

    public void setLangCode(String langCode) {
        lang = langCode;
    }
}
