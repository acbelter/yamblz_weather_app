package com.acbelter.weatherapp.domain.repository;


import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;

import java.util.List;

import io.reactivex.Flowable;

public interface CityRepo {

    Flowable<List<AutocompleteData>> getCityList(CityParams cityParams);

    Flowable<CityData> getCityData(AutocompleteData autocompleteData);

    void saveCity(CityData cityData);

}
