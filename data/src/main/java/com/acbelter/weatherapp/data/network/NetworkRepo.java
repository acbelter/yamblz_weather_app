package com.acbelter.weatherapp.data.network;

import com.acbelter.weatherapp.data.locationmodel.Location;
import com.acbelter.weatherapp.data.placesmodel.Places;
import com.acbelter.weatherapp.data.weathermodel.currentweather.CurrentWeather;
import com.acbelter.weatherapp.data.weathermodel.forecast.ForecastWeather;
import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import io.reactivex.Flowable;

public interface NetworkRepo {
    Flowable<CurrentWeather> getCurrentWeather(WeatherParams params);

    Flowable<ForecastWeather> getForecastWeather(WeatherParams params);

    Flowable<Places> getPlaces(CityParams cityParams);

    Flowable<Location> getLocation(AutocompleteData autocompleteData);
}
