package com.acbelter.weatherapp.domain.model.city;

public class CityParams {

    private String mPartOfCityName;

    public CityParams(String partOfCityName) {
        mPartOfCityName = partOfCityName;
    }

    public String getPartOfCityName() {
        return mPartOfCityName;
    }

    public void setPartOfCityName(String partOfCityName) {
        mPartOfCityName = partOfCityName;
    }

}
