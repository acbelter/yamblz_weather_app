package com.acbelter.weatherapp.data.repository.city;

import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.repository.CityRepo;

import io.reactivex.Observable;
import timber.log.Timber;

public class CityRepoImpl implements CityRepo {

    private NetworkService mNetworkService;

    public CityRepoImpl(NetworkService networkService) {
        mNetworkService = networkService;
    }

    @Override
    public Observable<CityData> getCity(CityParams cityParams) {
        return mNetworkService.getLocation(cityParams)
                .map(CityDataConverter::fromNetworkData)
                .doOnNext(data -> {
                    Timber.d("Current city data from network: %s", data);
                });
    }
}
