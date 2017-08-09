package com.acbelter.weatherapp.data.network;

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

    private final WeatherApi weatherApi;
    private final PlacesApi placesApi;
    private final LocationApi locationApi;

    private String locale;

    private static final int NETWORK_TIMEOUT = 5000;

    public NetworkRepoImpl(WeatherApi weatherApi, PlacesApi placesApi, LocationApi locationApi) {
        this.weatherApi = weatherApi;
        this.placesApi = placesApi;
        this.locationApi = locationApi;
        this.locale = Locale.getDefault().getLanguage();
    }

    @Override
    public Single<CurrentWeather> getCurrentWeather(WeatherParams params) {
        double latitude = params.getCityData().getLatitude();
        double longitude = params.getCityData().getLongitude();
        return weatherApi.getCurrentWeather(latitude, longitude, locale);
    }

    @Override
    public Single<ForecastWeather> getForecastWeather(WeatherParams params) {
        double latitude = params.getCityData().getLatitude();
        double longitude = params.getCityData().getLongitude();
        return weatherApi.getForecast(latitude, longitude, locale);
    }

    @Override
    public Observable<Places> getPlaces(CityParams cityParams) {
        return placesApi.getPlaces(cityParams.getPartOfCityName().trim())
                .timeout(NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    @Override
    public Single<Location> getLocation(AutocompleteData autocompleteData) {
        return locationApi.getLocation(autocompleteData.getPlaceId(), locale);
    }
}
