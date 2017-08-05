package com.acbelter.weatherapp.data.network;

import com.acbelter.weatherapp.data.locationmodel.Location;
import com.acbelter.weatherapp.data.weathermodel.currentweather.CurrentWeather;
import com.acbelter.weatherapp.data.weathermodel.forecast.ExtendedWeather;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import io.reactivex.Flowable;

public interface NetworkService {
    Flowable<CurrentWeather> getCurrentWeather(WeatherParams params);

    Flowable<ExtendedWeather> getForecast(WeatherParams params);

    Flowable<Location> getLocation(CityParams cityParams);
}
