package com.acbelter.weatherapp.data.network;

import com.acbelter.weatherapp.data.locationmodel.Location;
import com.acbelter.weatherapp.data.netmodel.NetworkWeatherData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface NetworkService {
    Observable<NetworkWeatherData> getCurrentWeather(WeatherParams params);

    Observable<Location> getLocation(CityParams cityParams);
}
