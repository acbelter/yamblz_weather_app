package com.acbelter.weatherapp.data.network;

import android.support.annotation.WorkerThread;

import com.acbelter.weatherapp.data.weathermodel.currentweather.CurrentWeather;
import com.acbelter.weatherapp.data.weathermodel.forecast.ForecastWeather;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.acbelter.weatherapp.data.BuildConfig.OPEN_WEATHER_MAP_API_KEY;

public interface WeatherApi {

    String BASE_WEATHER_URL = "http://api.openweathermap.org/data/2.5/";

    @GET("weather?APPID=" + OPEN_WEATHER_MAP_API_KEY)
    @WorkerThread
    Single<CurrentWeather> getCurrentWeather(@Query("lat") double latitude,
                                             @Query("lon") double longitude,
                                             @Query("lang") String lang);

    @GET("forecast/daily?&cnt=16&APPID=" + OPEN_WEATHER_MAP_API_KEY)
    @WorkerThread
    Single<ForecastWeather> getForecast(@Query("lat") double latitude,
                                        @Query("lon") double longitude,
                                        @Query("lang") String lang);
}
