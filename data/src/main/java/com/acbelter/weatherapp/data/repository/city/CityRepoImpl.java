package com.acbelter.weatherapp.data.repository.city;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.acbelter.weatherapp.data.network.NetworkRepo;
import com.acbelter.weatherapp.data.repository.preference.SettingsPreference;
import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.repository.CityRepo;
import com.acbelter.weatherapp.domain.repository.DatabaseRepo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import timber.log.Timber;

public class CityRepoImpl implements CityRepo {

    @NonNull
    private final NetworkRepo networkRepo;
    @NonNull
    private final DatabaseRepo databaseRepo;
    @NonNull
    private final SettingsPreference settingsPreference;

    public CityRepoImpl(@NonNull NetworkRepo networkRepo, @NonNull DatabaseRepo databaseRepo, @NonNull SettingsPreference settingsPreference) {
        this.networkRepo = networkRepo;
        this.databaseRepo = databaseRepo;
        this.settingsPreference = settingsPreference;
    }

    @Override
    @WorkerThread
    public Single<List<AutocompleteData>> getCityList(@NonNull CityParams cityParams) {
        return networkRepo.getPlaces(cityParams)
                .flatMap(places -> Observable.fromIterable(places.getPredictions()))
                .map(CityDataConverter::convert)
                .toList()
                .doOnSuccess(data -> Timber.d("Current city data from network: %s", data));
    }

    @Override
    @WorkerThread
    public Single<List<CityData>> getFavoritesCities() {
        return databaseRepo.getAllCities();
    }

    @Override
    @WorkerThread
    public Single<CityData> getCityData(@Nullable AutocompleteData autocompleteData) {
        return networkRepo.getLocation(autocompleteData)
                .map(CityDataConverter::fromLocationToCityData);
    }

    @Override
    @WorkerThread
    public void saveCity(@NonNull CityData cityData) {
        settingsPreference.saveCurrentCity(cityData);
    }
}
