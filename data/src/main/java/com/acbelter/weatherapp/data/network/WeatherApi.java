package com.acbelter.weatherapp.data.network;

import com.acbelter.weatherapp.data.weathermodel.currentweather.CurrentWeather;
import com.acbelter.weatherapp.data.weathermodel.forecast.ExtendedWeather;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.acbelter.weatherapp.data.BuildConfig.OPEN_WEATHER_MAP_API_KEY;

public interface WeatherApi {

    String BASE_WEATHER_URL = "http://api.openweathermap.org/data/2.5/";

    @GET("weather?APPID=" + OPEN_WEATHER_MAP_API_KEY)
    Observable<CurrentWeather> getCurrentWeather(@Query("q") String city,
                                                 @Query("lang") String lang);

    @GET("weather?APPID=" + OPEN_WEATHER_MAP_API_KEY)
    Observable<ExtendedWeather> getForecast(@Query("q") String city,
                                            @Query("lang") String lang);
}
