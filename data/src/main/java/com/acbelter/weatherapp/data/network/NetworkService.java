package com.acbelter.weatherapp.data.network;

import com.acbelter.weatherapp.data.netmodel.NetworkWeatherData;

import java.util.List;

import io.reactivex.Observable;

public interface NetworkService {
    Observable<List<NetworkWeatherData>> getWeather();
}
