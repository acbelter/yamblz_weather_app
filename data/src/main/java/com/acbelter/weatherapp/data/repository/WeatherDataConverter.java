package com.acbelter.weatherapp.data.repository;

import com.acbelter.weatherapp.data.dbmodel.DatabaseWeatherData;
import com.acbelter.weatherapp.data.netmodel.NetworkWeatherData;
import com.acbelter.weatherapp.domain.model.WeatherData;

public class WeatherDataConverter {
    public static WeatherData fromDatabaseData(DatabaseWeatherData dbData) {
        if (dbData == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }
        return new WeatherData();
    }

    public static WeatherData fromNetworkData(NetworkWeatherData netData) {
        if (netData == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }
        return new WeatherData();
    }

    public static DatabaseWeatherData fromNetworkToDatabaseData(NetworkWeatherData netData) {
        if (netData == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }
        return new DatabaseWeatherData();
    }

    public static DatabaseWeatherData fromData(WeatherData data) {
        if (data == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }
        return new DatabaseWeatherData();
    }
}
