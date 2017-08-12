package com.acbelter.weatherapp.domain.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.repository.CityRepo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.Single;

public class CityInteractor {

    @NonNull
    private final CityRepo cityRepo;
    @NonNull
    private final Scheduler schedulerIO;
    @NonNull
    private final Scheduler schedulerMain;

    public CityInteractor(@NonNull CityRepo cityRepo, @NonNull Scheduler schedulerIO, @NonNull Scheduler schedulerMain) {
        this.cityRepo = cityRepo;
        this.schedulerIO = schedulerIO;
        this.schedulerMain = schedulerMain;
    }

    @WorkerThread
    public Single<List<AutocompleteData>> getCityList(@NonNull CityParams cityParams) {
        return cityRepo.getCityList(cityParams)
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    @WorkerThread
    public Single<List<CityData>> getFavorites() {
        return cityRepo.getFavoritesCities()
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    @WorkerThread
    public Single<CityData> getCityData(@NonNull AutocompleteData autocompleteData) {
        return cityRepo.getCityData(autocompleteData)
                .subscribeOn(schedulerIO)
                .observeOn(schedulerMain);
    }

    public void saveSelectedCity(@NonNull CityData cityData) {
        cityRepo.saveCity(cityData);
    }
}
