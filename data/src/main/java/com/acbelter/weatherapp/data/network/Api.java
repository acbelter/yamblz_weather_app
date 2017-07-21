package com.acbelter.weatherapp.data.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String OPEN_WEATHER_API_KEY = "5e09f6cd0f1eb1c1b5405f1327a62f7c";
    String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    @GET("weather?APPID=" + OPEN_WEATHER_API_KEY)
    Observable<String> getCurrentWeatherData(@Query("q") String city,
                                             @Query("lang") String lang);
}
