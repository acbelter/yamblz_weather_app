package com.acbelter.weatherapp.data.repository.city;

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

    private NetworkRepo networkRepo;
    private DatabaseRepo databaseRepo;
    private SettingsPreference settingsPreference;

    public CityRepoImpl(NetworkRepo networkRepo, DatabaseRepo databaseRepo, SettingsPreference settingsPreference) {
        this.networkRepo = networkRepo;
        this.databaseRepo = databaseRepo;
        this.settingsPreference = settingsPreference;
    }

    @Override
    public Single<List<AutocompleteData>> getCityList(CityParams cityParams) {
        return networkRepo.getPlaces(cityParams)
                .flatMap(places -> Observable.fromIterable(places.getPredictions()))
                .map(CityDataConverter::convert)
                .toList()
                .doOnSuccess(data -> {
                    Timber.d("Current city data from network: %s", data);
                });
    }

    @Override
    public Flowable<List<CityData>> getFavoritesCities() {
        return databaseRepo.getAllCities();
    }

    @Override
    public Single<CityData> getCityData(AutocompleteData autocompleteData) {
        return networkRepo.getLocation(autocompleteData)
                .map(CityDataConverter::fromLocationToCityData);
    }

    @Override
    public void saveCity(CityData cityData) {
        settingsPreference.saveCurrentCity(cityData);
    }
}
