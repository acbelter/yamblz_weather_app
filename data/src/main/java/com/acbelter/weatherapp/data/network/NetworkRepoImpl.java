package com.acbelter.weatherapp.data.network;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.acbelter.weatherapp.data.locationmodel.Location;
import com.acbelter.weatherapp.data.placesmodel.Places;
import com.acbelter.weatherapp.data.weathermodel.currentweather.CurrentWeather;
import com.acbelter.weatherapp.data.weathermodel.forecast.ForecastWeather;
import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;

public class NetworkRepoImpl implements NetworkRepo {

    @NonNull
    private final WeatherApi weatherApi;

    @NonNull
    private final PlacesApi placesApi;

    @NonNull
    private final LocationApi locationApi;

    @NonNull
    private String locale;

    private static final int NETWORK_TIMEOUT = 5000;

    public NetworkRepoImpl(@NonNull WeatherApi weatherApi, @NonNull PlacesApi placesApi, @NonNull LocationApi locationApi) {
        this.weatherApi = weatherApi;
        this.placesApi = placesApi;
        this.locationApi = locationApi;
        this.locale = Locale.getDefault().getLanguage();
    }

    @Override
    @WorkerThread
    public Single<CurrentWeather> getCurrentWeather(@NonNull WeatherParams params) {
        double latitude = params.getCityData().getLatitude();
        double longitude = params.getCityData().getLongitude();
        return weatherApi.getCurrentWeather(latitude, longitude, locale);
    }

    @Override
    @WorkerThread
    public Single<ForecastWeather> getForecastWeather(@NonNull WeatherParams params) {
        double latitude = params.getCityData().getLatitude();
        double longitude = params.getCityData().getLongitude();
        return weatherApi.getForecast(latitude, longitude, locale);
    }

    @Override
    @WorkerThread
    public Observable<Places> getPlaces(@NonNull CityParams cityParams) {
        return placesApi.getPlaces(cityParams.getPartOfCityName().trim())
                .timeout(NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    @Override
    @WorkerThread
    public Single<Location> getLocation(@NonNull AutocompleteData autocompleteData) {
        return locationApi.getLocation(autocompleteData.getPlaceId(), locale);
    }
}
