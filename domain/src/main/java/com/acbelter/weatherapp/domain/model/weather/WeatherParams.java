package com.acbelter.weatherapp.domain.model.weather;

public class WeatherParams {
    private String mCity;
    private String mLangCode;

    public WeatherParams(String city) {
        mCity = city;
        mLangCode = "en";
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getLangCode() {
        return mLangCode;
    }

    public void setLangCode(String langCode) {
        mLangCode = langCode;
    }
}
