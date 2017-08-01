package com.acbelter.weatherapp.domain.model.city;

public class CityParams {

    private String mPartOfCityName;
    private String mLangCode;

    public CityParams(String partOfCityName) {
        mPartOfCityName = partOfCityName;
        mLangCode = "en";
    }

    public String getPartOfCityName() {
        return mPartOfCityName;
    }

    public void setPartOfCityName(String partOfCityName) {
        mPartOfCityName = partOfCityName;
    }

    public String getLangCode() {
        return mLangCode;
    }

    public void setLangCode(String langCode) {
        mLangCode = langCode;
    }
}
