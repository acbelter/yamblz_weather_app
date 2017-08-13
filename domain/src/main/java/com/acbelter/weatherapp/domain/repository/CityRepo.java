package com.acbelter.weatherapp.domain.repository;


import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface CityRepo {

    @WorkerThread
    Single<List<AutocompleteData>> getCityList(@NonNull CityParams cityParams);

    @WorkerThread
    Flowable<List<CityData>> getFavoritesCities();

    @WorkerThread
    Single<CityData> getCityData(@Nullable AutocompleteData autocompleteData);

    @MainThread
    void saveCity(@NonNull CityData cityData);

}
