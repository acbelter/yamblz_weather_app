package com.acbelter.weatherapp.data.network;

import com.acbelter.weatherapp.data.locationmodel.Location;
import com.acbelter.weatherapp.data.netmodel.CurrentWeather;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class NetworkServiceImpl implements NetworkService {

    private WeatherApi weatherApi;
    private PlacesApi placesApi;
    private LocationApi locationApi;

    private static final int NETWORK_TIMEOUT = 5000;

    public NetworkServiceImpl(WeatherApi weatherApi, PlacesApi placesApi, LocationApi locationApi) {
        this.weatherApi = weatherApi;
        this.placesApi = placesApi;
        this.locationApi = locationApi;
    }

    @Override
    public Observable<CurrentWeather> getCurrentWeather(WeatherParams params) {
        return weatherApi.getCurrentWeather(params.getCityName(), params.getLangCode());
    }

    @Override
    public Observable<Location> getLocation(CityParams cityParams) {
        return placesApi.getPlaces(cityParams.getPartOfCityName().trim())
                .timeout(NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
                .flatMap(places ->
                        Observable.fromIterable(places.getPredictions()))
                .concatMap(prediction -> locationApi.getLocation(prediction.getPlaceId(), cityParams.getLangCode()));
    }
}
