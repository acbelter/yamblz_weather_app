package com.acbelter.weatherapp.data.network;

import com.acbelter.weatherapp.data.locationmodel.Location;
import com.acbelter.weatherapp.data.weathermodel.currentweather.CurrentWeather;
import com.acbelter.weatherapp.data.weathermodel.forecast.ExtendedWeather;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import io.reactivex.Observable;

public interface NetworkService {
    Observable<CurrentWeather> getCurrentWeather(WeatherParams params);

    Observable<ExtendedWeather> getForecast(WeatherParams params);

    Observable<Location> getLocation(CityParams cityParams);
}
