package com.acbelter.weatherapp.data.repository;

import com.acbelter.weatherapp.data.dbmodel.DatabaseWeatherData;
import com.acbelter.weatherapp.data.netmodel.NetworkWeatherData;
import com.acbelter.weatherapp.data.netmodel.Weather;
import com.acbelter.weatherapp.domain.model.WeatherData;
import com.acbelter.weatherapp.domain.model.WeatherType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WeatherDataConverter {
    public static WeatherData fromDatabaseData(DatabaseWeatherData dbData) {
        if (dbData == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }
        throw new UnsupportedOperationException();
    }

    public static WeatherData fromNetworkData(NetworkWeatherData netData) {
        if (netData == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        if (netData.cod != 200) {
            return null;
        }

        WeatherData weatherData = new WeatherData();
        weatherData.setCity(netData.name);
        weatherData.setTemperatureK(netData.main.temp);
        weatherData.setWeatherType(extractWeatherType(netData.weather));
        weatherData.setTimestamp(netData.dt);
        weatherData.setSunriseTimestamp(netData.sys.sunrise);
        weatherData.setSunsetTimestamp(netData.sys.sunset);
        return weatherData;
    }

    private static WeatherType extractWeatherType(List<Weather> weatherList) {
        Set<String> weatherStringTypes = new HashSet<>();
        for (Weather weather : weatherList) {
            weatherStringTypes.add(weather.main);
        }

        if (weatherStringTypes.contains("Thunderstorm")) {
            return WeatherType.STORM;
        }

        if (weatherStringTypes.contains("Rain") || weatherStringTypes.contains("Drizzle")) {
            return WeatherType.RAIN;
        }

        if (weatherStringTypes.contains("Snow")) {
            return WeatherType.SNOW;
        }

        if (weatherStringTypes.contains("Clouds")) {
            return WeatherType.CLOUDS;
        }

        return WeatherType.SUN;
    }

    public static DatabaseWeatherData fromNetworkToDatabaseData(NetworkWeatherData netData) {
        if (netData == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }
        throw new UnsupportedOperationException();
    }

    public static DatabaseWeatherData fromData(WeatherData data) {
        if (data == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }
        throw new UnsupportedOperationException();
    }
}
