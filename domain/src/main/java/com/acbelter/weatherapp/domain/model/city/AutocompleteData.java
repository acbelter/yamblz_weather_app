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

    public int hashCode() {
        int result = 17;
        result = 31 * result + (cityName == null ? 0 : cityName.hashCode());
        result = 31 * result + (placeId == null ? 0 : placeId.hashCode());
        return result;
    }
}
