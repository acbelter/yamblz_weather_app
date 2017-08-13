package com.acbelter.weatherapp.domain.model.fullmodel;

import android.support.annotation.NonNull;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherElement;

import java.util.List;

public class FullWeatherModel {

    @NonNull
    private CityData cityData;
    @NonNull
    private CurrentWeatherFavorites currentWeatherFavorites;
    @NonNull
    private List<ForecastWeatherElement> forrecast;

    public FullWeatherModel(@NonNull CityData cityData, @NonNull CurrentWeatherFavorites currentWeatherFavorites, @NonNull List<ForecastWeatherElement> forrecast) {
        this.cityData = cityData;
        this.currentWeatherFavorites = currentWeatherFavorites;
        this.forrecast = forrecast;

    }

    @NonNull
    public CityData getCityData() {
        return cityData;
    }

    public void setCityData(@NonNull CityData cityData) {
        this.cityData = cityData;
    }

    @NonNull
    public CurrentWeatherFavorites getCurrentWeatherFavorites() {
        return currentWeatherFavorites;
    }

    public void setCurrentWeatherFavorites(@NonNull CurrentWeatherFavorites currentWeatherFavorites) {
        this.currentWeatherFavorites = currentWeatherFavorites;
    }

    @NonNull
    public List<ForecastWeatherElement> getForrecast() {
        return forrecast;
    }

    public void setForrecast(@NonNull List<ForecastWeatherElement> forrecast) {
        this.forrecast = forrecast;
    }
}
