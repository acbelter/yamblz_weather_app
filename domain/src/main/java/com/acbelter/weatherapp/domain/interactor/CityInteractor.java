package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.repository.CityRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class CityInteractor {

    private CityRepo mCityRepo;

    @Inject
    public CityInteractor(CityRepo cityRepo) {
        mCityRepo = cityRepo;
    }

    public Single<List<CityData>> getCityList(CityParams cityParams) {
        return mCityRepo.getCity(cityParams).toList();
    }

    public void saveSelectedCity(CityData cityData) {
        mCityRepo.saveCity(cityData);
    }
}
