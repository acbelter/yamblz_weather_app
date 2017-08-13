package com.acbelter.weatherapp.data.repository.database;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.acbelter.weatherapp.data.dbmodel.DatabaseWeatherData;
import com.acbelter.weatherapp.data.weathermodel.common.Coord;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherElement;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

class DatabaseWeatherConverter {

    private DatabaseWeatherConverter() {
    }

    @NonNull
    static CityData fromDatabaseWeatherDataToCityData(@Nullable DatabaseWeatherData databaseWeatherData) {

        if (databaseWeatherData == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        Gson gson = new Gson();
        Coord coord = gson.fromJson(databaseWeatherData.getCoordinates(), Coord.class);
        long timestamp = gson.fromJson(databaseWeatherData.getTimestamp(), Long.class);
        return new CityData.Builder(coord.getLat(), coord.getLon(), timestamp)
                .shortName(databaseWeatherData.getCityShortName()).build();
    }

    static
    @NonNull
    CurrentWeatherFavorites fromDatabaseWeatherDataToCurrentWeatherData(@Nullable DatabaseWeatherData databaseWeatherData) {

        if (databaseWeatherData == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        return new Gson().fromJson(databaseWeatherData.getCurrent(), CurrentWeatherFavorites.class);
    }

    static
    @NonNull
    List<ForecastWeatherElement> fromDatabaseWeatherDataToForecastWeather(@Nullable DatabaseWeatherData databaseWeatherData) {

        if (databaseWeatherData == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        return new Gson()
                .fromJson(databaseWeatherData.getForecast()
                        , new TypeToken<List<ForecastWeatherElement>>() {
                        }.getType());
    }

    static
    @NonNull
    DatabaseWeatherData fromFullWeatherDataToDatabaseFormat(@Nullable FullWeatherModel fullWeatherModel) {

        if (fullWeatherModel == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        Gson gson = new Gson();
        String shortName = fullWeatherModel.getCityData().getShortName();
        double latitude = fullWeatherModel.getCityData().getLatitude();
        double longitude = fullWeatherModel.getCityData().getLongitude();
        Coord coord = new Coord(latitude, longitude);
        String coordinates = gson.toJson(coord);
        String currentWeather = gson.toJson(fullWeatherModel.getCurrentWeatherFavorites());
        String forecast = gson.toJson(fullWeatherModel.getForrecast());
        String timestamp = gson.toJson(fullWeatherModel.getCityData().getTimestamp());

        DatabaseWeatherData databaseWeatherData = new DatabaseWeatherData();
        databaseWeatherData.setCoordinates(coordinates);
        if (shortName != null)
            databaseWeatherData.setCityShortName(shortName);
        databaseWeatherData.setCurrent(currentWeather);
        databaseWeatherData.setForecast(forecast);
        databaseWeatherData.setTimestamp(timestamp);
        return databaseWeatherData;
    }
}
