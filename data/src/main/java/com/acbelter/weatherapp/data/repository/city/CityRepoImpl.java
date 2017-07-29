package com.acbelter.weatherapp.data.repository.city;

import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.data.repository.PreferencesRepo;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.repository.CityRepo;

import io.reactivex.Observable;
import timber.log.Timber;

public class CityRepoImpl implements CityRepo {

    private NetworkService mNetworkService;
    private PreferencesRepo mPreferencesRepo;

    public CityRepoImpl(NetworkService networkService, PreferencesRepo preferencesRepo) {
        mNetworkService = networkService;
        mPreferencesRepo = preferencesRepo;
    }

    @Override
    public Observable<CityData> getCity(CityParams cityParams) {
        return mNetworkService.getLocation(cityParams)
                .map(CityDataConverter::fromNetworkData)
                .doOnNext(data -> {
                    Timber.d("Current city data from network: %s", data);
                });
    }

    @Override
    public void saveCity(CityData cityData) {
        mPreferencesRepo.setCurrentCity(cityData.getFormattedAddress());
    }
}
