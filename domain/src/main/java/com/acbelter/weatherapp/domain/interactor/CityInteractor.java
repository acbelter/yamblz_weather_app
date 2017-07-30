package com.acbelter.weatherapp.domain.interactor;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.repository.CityRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class CityInteractor {

    private CityRepo mCityRepo;
    private Scheduler mSchedulerIO;
    private Scheduler mSchedulerMain;

    @Inject
    public CityInteractor(CityRepo cityRepo, Scheduler schedulerIO, Scheduler schedulerMain) {
        mCityRepo = cityRepo;
        mSchedulerIO = schedulerIO;
        mSchedulerMain = schedulerMain;
    }

    public Single<List<CityData>> getCityList(CityParams cityParams) {
        return mCityRepo.getCity(cityParams)
                .toList()
                .subscribeOn(mSchedulerIO)
                .observeOn(mSchedulerMain);
    }

    public void saveSelectedCity(CityData cityData) {
        mCityRepo.saveCity(cityData);
    }
}
