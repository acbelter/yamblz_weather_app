package com.acbelter.weatherapp.domain.repository;


import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;

import io.reactivex.Observable;

public interface CityRepo {

    Observable<CityData> getCity(CityParams cityParams);

    void saveCity(CityData cityData);

}
