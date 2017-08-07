package com.acbelter.weatherapp.data.repository.city;

import com.acbelter.weatherapp.data.network.NetworkRepo;
import com.acbelter.weatherapp.data.repository.preference.SettingsPreference;
import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.repository.CityRepo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import timber.log.Timber;

public class CityRepoImpl implements CityRepo {

    private NetworkRepo networkRepo;
    private SettingsPreference settingsPreference;

    public CityRepoImpl(NetworkRepo networkRepo, SettingsPreference settingsPreference) {
        this.networkRepo = networkRepo;
        this.settingsPreference = settingsPreference;
    }

    @Override
    public Observable<List<AutocompleteData>> getCityList(CityParams cityParams) {
        return networkRepo.getPlaces(cityParams)
                .map(CityDataConverter::fromPlacesToDataList)
                .doOnNext(data -> {
                    Timber.d("Current city data from network: %s", data);
                });
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
