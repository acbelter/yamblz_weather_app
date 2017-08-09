package com.acbelter.weatherapp.domain.model.fullmodel;

import android.support.annotation.NonNull;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherFavorites;

import java.util.List;

public class FullWeatherModel {

    @NonNull
    private CityData cityData;
    @NonNull
    private CurrentWeatherFavorites currentWeatherFavorites;
    @NonNull
    private List<ForecastWeatherFavorites> forrecast;

    public FullWeatherModel(@NonNull CityData cityData, @NonNull CurrentWeatherFavorites currentWeatherFavorites, @NonNull List<ForecastWeatherFavorites> forrecast) {
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
    public List<ForecastWeatherFavorites> getForrecast() {
        return forrecast;
    }

    public void setForrecast(@NonNull List<ForecastWeatherFavorites> forrecast) {
        this.forrecast = forrecast;
    }
}
