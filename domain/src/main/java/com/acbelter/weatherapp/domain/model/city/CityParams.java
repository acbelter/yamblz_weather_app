package com.acbelter.weatherapp.domain.model.city;

import android.support.annotation.NonNull;

public class CityParams {

    @NonNull
    private String partOfCityName;

    public CityParams(@NonNull String partOfCityName) {
        this.partOfCityName = partOfCityName;
    }

    @NonNull
    public String getPartOfCityName() {
        return partOfCityName;
    }
}
