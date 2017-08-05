package com.acbelter.weatherapp.domain.repository;


import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;

import io.reactivex.Flowable;

public interface CityRepo {

    Flowable<CityData> getCity(CityParams cityParams);

    void saveCity(CityData cityData);

}
