package com.acbelter.weatherapp.domain.repository;


import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface CityRepo {

    Observable<List<AutocompleteData>> getCityList(CityParams cityParams);

    Single<CityData> getCityData(AutocompleteData autocompleteData);

    void saveCity(CityData cityData);

}
