package com.acbelter.weatherapp.domain.repository;


import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;

import java.util.List;

import io.reactivex.Single;

public interface CityRepo {

    Single<List<AutocompleteData>> getCityList(CityParams cityParams);

    Single<List<CityData>> getFavoritesCities();

    Single<CityData> getCityData(AutocompleteData autocompleteData);

    void saveCity(CityData cityData);

}
