package com.acbelter.weatherapp.domain.model.fullmodel;

import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherFavorites;

import java.util.List;

public class FullWeatherModel {

    private CityData cityData;
    private CurrentWeatherFavorites currentWeatherFavorites;
    private List<ForecastWeatherFavorites> forrecast;

    public FullWeatherModel(CityData cityData, CurrentWeatherFavorites currentWeatherFavorites, List<ForecastWeatherFavorites> forrecast) {
        this.cityData = cityData;
        this.currentWeatherFavorites = currentWeatherFavorites;
        this.forrecast = forrecast;

    }

    public CityData getCityData() {
        return cityData;
    }

    public void setCityData(CityData cityData) {
        this.cityData = cityData;
    }

    public CurrentWeatherFavorites getCurrentWeatherFavorites() {
        return currentWeatherFavorites;
    }

    public void setCurrentWeatherFavorites(CurrentWeatherFavorites currentWeatherFavorites) {
        this.currentWeatherFavorites = currentWeatherFavorites;
    }

    public List<ForecastWeatherFavorites> getForrecast() {
        return forrecast;
    }

    public void setForrecast(List<ForecastWeatherFavorites> forrecast) {
        this.forrecast = forrecast;
    }
}
