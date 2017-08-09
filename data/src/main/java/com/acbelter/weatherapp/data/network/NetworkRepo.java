package com.acbelter.weatherapp.data.network;

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

    Single<CurrentWeather> getCurrentWeather(WeatherParams params);

    Single<ForecastWeather> getForecastWeather(WeatherParams params);

    Observable<Places> getPlaces(CityParams cityParams);

    Single<Location> getLocation(AutocompleteData autocompleteData);
}
