package com.acbelter.weatherapp.data.network;

import com.acbelter.weatherapp.data.locationmodel.Location;
import com.acbelter.weatherapp.data.weathermodel.currentweather.CurrentWeather;
import com.acbelter.weatherapp.data.weathermodel.forecast.ForecastWeather;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

public class NetworkRepoImpl implements NetworkRepo {

    private WeatherApi weatherApi;
    private PlacesApi placesApi;
    private LocationApi locationApi;

    private String locale;

    private static final int NETWORK_TIMEOUT = 5000;

    public NetworkRepoImpl(WeatherApi weatherApi, PlacesApi placesApi, LocationApi locationApi) {
        this.weatherApi = weatherApi;
        this.placesApi = placesApi;
        this.locationApi = locationApi;
        this.locale = Locale.getDefault().getLanguage();
    }

    @Override
    public Flowable<CurrentWeather> getCurrentWeather(WeatherParams params) {
        return weatherApi.getCurrentWeather(params.getCityData().getFormattedAddress(), locale);
    }

    @Override
    public Flowable<ForecastWeather> getForecastWeather(WeatherParams params) {
        return weatherApi.getForecast(params.getCityData().getFormattedAddress(), locale);
    }

    @Override
    public Flowable<Location> getLocation(CityParams cityParams) {
        return placesApi.getPlaces(cityParams.getPartOfCityName().trim())
                .timeout(NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
                .flatMap(places ->
                        Flowable.fromIterable(places.getPredictions()))
                .concatMap(prediction -> locationApi.getLocation(prediction.getPlaceId(), locale));
    }
}
