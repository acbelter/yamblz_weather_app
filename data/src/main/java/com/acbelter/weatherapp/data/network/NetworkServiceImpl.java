package com.acbelter.weatherapp.data.network;

import com.acbelter.weatherapp.data.netmodel.NetworkWeatherData;
import com.acbelter.weatherapp.domain.model.WeatherParams;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.reactivex.Observable;

public class NetworkServiceImpl implements NetworkService {
    private Api mApi;

    public NetworkServiceImpl(Api api) {
        mApi = api;
    }

    @Override
    public Observable<NetworkWeatherData> getCurrentWeather(WeatherParams params) {
        return mApi.getCurrentWeatherData(params.getCity(), params.getLangCode()).map(data -> {
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
}
