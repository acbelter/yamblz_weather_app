package com.acbelter.weatherapp.data.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.acbelter.weatherapp.data.locationmodel.Location;
import com.acbelter.weatherapp.data.placesmodel.Places;
import com.acbelter.weatherapp.data.weathermodel.currentweather.CurrentWeather;
import com.acbelter.weatherapp.data.weathermodel.forecast.ForecastWeather;
import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface NetworkRepo {

    @WorkerThread
    Single<CurrentWeather> getCurrentWeather(@NonNull WeatherParams params);

    @WorkerThread
    Single<ForecastWeather> getForecastWeather(@NonNull WeatherParams params);

    @WorkerThread
    Observable<Places> getPlaces(@NonNull CityParams cityParams);

    @WorkerThread
    Single<Location> getLocation(@Nullable AutocompleteData autocompleteData);
}
