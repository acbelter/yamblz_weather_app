package com.acbelter.weatherapp.data.network;

import com.acbelter.weatherapp.data.netmodel.NetworkWeatherData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "http://weather_server_base_url";
    @GET("/weather")
    Observable<List<NetworkWeatherData>> getWeather();
}
