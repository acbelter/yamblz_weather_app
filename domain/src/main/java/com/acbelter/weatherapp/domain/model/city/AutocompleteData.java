package com.acbelter.weatherapp.domain.model.city;

import android.support.annotation.Nullable;

public class AutocompleteData {

    @Nullable
    private String cityName;
    @Nullable
    private String placeId;

    public AutocompleteData(@Nullable String cityName, @Nullable String placeId) {
        this.cityName = cityName;
        this.placeId = placeId;
    }

    @Nullable
    public String getCityName() {
        return cityName;
    }

    @Nullable
    public String getPlaceId() {
        return placeId;
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + (cityName == null ? 0 : cityName.hashCode());
        result = 31 * result + (placeId == null ? 0 : placeId.hashCode());
        return result;
    }
}
