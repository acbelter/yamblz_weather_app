package com.acbelter.weatherapp.data.network;

import com.acbelter.weatherapp.data.locationmodel.Location;
import com.acbelter.weatherapp.data.netmodel.NetworkWeatherData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.reactivex.Observable;

public class NetworkServiceImpl implements NetworkService {
    private WeatherApi mWeatherApi;
    private PlacesApi mPlacesApi;
    private LocationApi mLocationApi;

    public NetworkServiceImpl(WeatherApi weatherApi, PlacesApi placesApi, LocationApi locationApi) {
        mWeatherApi = weatherApi;
        mPlacesApi = placesApi;
        mLocationApi = locationApi;
    }

    @Override
    public Observable<NetworkWeatherData> getCurrentWeather(WeatherParams params) {
        return mWeatherApi.getCurrentWeatherData(params.getCity(), params.getLangCode()).map(data -> {
            JsonParser parser = new JsonParser();
            Gson gson = new Gson();
            JsonObject rootObject = parser.parse(data).getAsJsonObject();
            if (!rootObject.has("list")) {
                // If data has one weather condition, return it
                return gson.fromJson(rootObject, NetworkWeatherData.class);
            } else {
                // If data has several weather conditions, return first condition
                return gson.fromJson(rootObject.getAsJsonArray("list").get(0), NetworkWeatherData.class);
            }
        });
    }

    @Override
    public Observable<Location> getLocation(CityParams cityParams) {
        return mPlacesApi.getPlaces(cityParams.getPartOfCityName().trim())
                .flatMap(places ->
                        Observable.fromIterable(places.getPredictions()))
                .concatMap(prediction -> mLocationApi.getLocation(prediction.getPlaceId(), cityParams.getLangCode()));
    }
}
