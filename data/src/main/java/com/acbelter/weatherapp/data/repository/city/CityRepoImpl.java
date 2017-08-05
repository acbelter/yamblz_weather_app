package com.acbelter.weatherapp.data.repository.city;

import com.acbelter.weatherapp.data.network.NetworkRepo;
import com.acbelter.weatherapp.data.repository.preference.SettingsPreference;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.repository.CityRepo;

import io.reactivex.Flowable;
import timber.log.Timber;

public class CityRepoImpl implements CityRepo {

    private NetworkRepo mNetworkRepo;
    private SettingsPreference settingsPreference;

    public CityRepoImpl(NetworkRepo networkRepo, SettingsPreference settingsPreference) {
        mNetworkRepo = networkRepo;
        this.settingsPreference = settingsPreference;
    }

    @Override
    public Flowable<CityData> getCity(CityParams cityParams) {
        return mNetworkRepo.getLocation(cityParams)
                .map(CityDataConverter::fromNetworkData)
                .doOnNext(data -> {
                    Timber.d("Current city data from network: %s", data);
                });
    }

    @Override
    public void saveCity(CityData cityData) {
        settingsPreference.saveCurrentCity(cityData);
    }
}
