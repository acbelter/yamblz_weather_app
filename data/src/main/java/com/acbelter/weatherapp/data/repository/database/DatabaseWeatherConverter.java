package com.acbelter.weatherapp.data.repository.database;

import com.acbelter.weatherapp.data.dbmodel.DatabaseWeatherData;
import com.acbelter.weatherapp.data.weathermodel.common.Coord;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;
import com.google.gson.Gson;

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

    public static CurrentWeatherData fromDatabaseWeatherDataToCurrentWeatherData(DatabaseWeatherData databaseWeatherData) {
        if (databaseWeatherData == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        return new Gson().fromJson(databaseWeatherData.getCurrentWeather(), CurrentWeatherData.class);
    }

    public static WeatherForecast fromDatabaseWeatherDataToForecastWeather(DatabaseWeatherData databaseWeatherData) {
        if (databaseWeatherData == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        return new Gson().fromJson(databaseWeatherData.getForecast(), WeatherForecast.class);
    }

    public static DatabaseWeatherData fromFullWeatherDataToDatabaseFormat(FullWeatherModel fullWeatherModel) {
        if (fullWeatherModel == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        Gson gson = new Gson();
        String shortName = fullWeatherModel.getCityData().getShortName();
        double latitude = fullWeatherModel.getCityData().getLatitude();
        double longitude = fullWeatherModel.getCityData().getLongitude();
        Coord coord = new Coord(latitude, longitude);
        String coordinates = gson.toJson(coord);
        String currentWeather = gson.toJson(fullWeatherModel.getCurrentWeatherData());
        String forecast = gson.toJson(fullWeatherModel.getForrecast());

        DatabaseWeatherData databaseWeatherData = new DatabaseWeatherData();
        databaseWeatherData.setCoordinates(coordinates);
        databaseWeatherData.setCityShortName(shortName);
        databaseWeatherData.setCurrentWeather(currentWeather);
        databaseWeatherData.setForecast(forecast);
        return databaseWeatherData;
    }
}
