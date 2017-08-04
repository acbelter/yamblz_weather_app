package com.acbelter.weatherapp.data.repository.database;

import com.acbelter.weatherapp.data.dbmodel.DatabaseWeatherData;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;
import com.google.gson.Gson;

public class DatabaseWeatherConverter {

    public static FullWeatherModel fullWeatherFromDatabase(DatabaseWeatherData databaseWeatherData) {
        if (databaseWeatherData == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        Gson gson = new Gson();
        CityData cityData = gson.fromJson(databaseWeatherData.getCityData(), CityData.class);
        WeatherData weatherData = gson.fromJson(databaseWeatherData.getCurrentWeather(), WeatherData.class);
        WeatherForecast weatherForecast = gson.fromJson(databaseWeatherData.getForecast(), WeatherForecast.class);

        return new FullWeatherModel(cityData, weatherData, weatherForecast);
    }

    public static DatabaseWeatherData fromFullWeatherDataToDatabaseFormat(FullWeatherModel fullWeatherModel) {
        if (fullWeatherModel == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        Gson gson = new Gson();
        String cityData = gson.toJson(fullWeatherModel.getCityData());
        String currentWeather = gson.toJson(fullWeatherModel.getWeatherData());
        String forecast = gson.toJson(fullWeatherModel.getForrecast());

        DatabaseWeatherData databaseWeatherData = new DatabaseWeatherData();
        databaseWeatherData.setCityData(cityData);
        databaseWeatherData.setCurrentWeather(currentWeather);
        databaseWeatherData.setForecast(forecast);
        return databaseWeatherData;
    }
}
