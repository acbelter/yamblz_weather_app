package com.acbelter.weatherapp.data.network;

import com.acbelter.weatherapp.data.netmodel.NetworkWeatherData;
import com.acbelter.weatherapp.domain.model.WeatherParams;

import io.reactivex.Observable;

public interface NetworkService {
    Observable<NetworkWeatherData> getCurrentWeather(WeatherParams params);
}
