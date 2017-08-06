package com.acbelter.weatherapp.domain.model.city;

public class AutocompleteData {

    private String cityName;
    private String placeId;

    public AutocompleteData(String cityName, String placeId) {
        this.cityName = cityName;
        this.placeId = placeId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}
