package com.acbelter.weatherapp.data.repository.database;

import com.acbelter.weatherapp.data.dbmodel.DatabaseWeatherData;
import com.acbelter.weatherapp.data.weathermodel.common.Coord;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherFavorites;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class DatabaseWeatherConverter {

    public static CityData fromDatabaseWeatherDataToCityData(DatabaseWeatherData databaseWeatherData) {
        if (databaseWeatherData == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        CityData cityData = new CityData();
        Coord coord = new Gson().fromJson(databaseWeatherData.getCoordinates(), Coord.class);
        cityData.setLatitude(coord.getLat());
        cityData.setLongitude(coord.getLon());
        cityData.setShortName(databaseWeatherData.getCityShortName());
        return cityData;
    }

    public static CurrentWeatherFavorites fromDatabaseWeatherDataToCurrentWeatherData(DatabaseWeatherData databaseWeatherData) {
        if (databaseWeatherData == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        return new Gson().fromJson(databaseWeatherData.getCurrentWeather(), CurrentWeatherFavorites.class);
    }

    public static List<ForecastWeatherFavorites> fromDatabaseWeatherDataToForecastWeather(DatabaseWeatherData databaseWeatherData) {
        if (databaseWeatherData == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        Gson gson = new Gson();

        return (List<ForecastWeatherFavorites>) gson
                .fromJson(databaseWeatherData.getForecast()
                        , new TypeToken<List<ForecastWeatherFavorites>>() {
                        }.getType());
    }

    public static DatabaseWeatherData fromFullWeatherDataToDatabaseFormat(FullWeatherModel fullWeatherModel) {
        if (fullWeatherModel == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        Gson gson = new Gson();
        String shortName = fullWeatherModel.getCityData().getShortName();
        double latitude = fullWeatherModel.getCityData().getLatitude();
        double longitude = fullWeatherModel.getCityData().getLongitude();
        Coord coord = new Coord(longitude, latitude);
        String coordinates = gson.toJson(coord);
        String currentWeather = gson.toJson(fullWeatherModel.getCurrentWeatherFavorites());
        String forecast = gson.toJson(fullWeatherModel.getForrecast());

        DatabaseWeatherData databaseWeatherData = new DatabaseWeatherData();
        databaseWeatherData.setCoordinates(coordinates);
        databaseWeatherData.setCityShortName(shortName);
        databaseWeatherData.setCurrentWeather(currentWeather);
        databaseWeatherData.setForecast(forecast);
        return databaseWeatherData;
    }
}
