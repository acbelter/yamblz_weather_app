package com.acbelter.weatherapp.data.repository.weather;

import android.support.annotation.VisibleForTesting;

import com.acbelter.weatherapp.data.dbmodel.DatabaseWeatherData;
import com.acbelter.weatherapp.data.weathermodel.currentweather.CurrentWeather;
import com.acbelter.weatherapp.data.weathermodel.currentweather.Weather;
import com.acbelter.weatherapp.data.weathermodel.forecast.ExtendedWeather;
import com.acbelter.weatherapp.data.weathermodel.forecast.WeatherForecastElement;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherType;

import java.util.ArrayList;
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

    public static WeatherData currentWeatherFromNetworkData(CurrentWeather currentWeather, WeatherParams weatherParams) {
        if (currentWeather == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        if (currentWeather.getCod() != 200) {
            return null;
        }

        WeatherData weatherData = new WeatherData();
        weatherData.setCity(weatherParams.getCityName());
        weatherData.setTemperatureK(currentWeather.getMain().getTemp());
        weatherData.setWeatherType(extractWeatherType(currentWeather.getWeather()));
        weatherData.setTimestamp((long) currentWeather.getDt() * 1000);
        weatherData.setSunriseTimestamp((long) currentWeather.getSys().getSunrise() * 1000);
        weatherData.setSunsetTimestamp((long) currentWeather.getSys().getSunset() * 1000);
        return weatherData;
    }

    public static WeatherForecast forecastFromNetworkData(ExtendedWeather extendedWeather, WeatherParams weatherParams) {
        if (extendedWeather == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        if (Integer.valueOf(extendedWeather.getCod()) != 200) {
            return null;
        }

        WeatherForecast weatherForecast = new WeatherForecast();
        List<WeatherForecastElement> forecastList = extendedWeather.getList();
        List<WeatherData> forecastWeatherData = new ArrayList<>();
        for (WeatherForecastElement element : forecastList) {
            WeatherData weatherData = new WeatherData();
            weatherData.setCity(weatherParams.getCityName());
            weatherData.setWeatherType(extractWeatherType(element.getWeather()));
            weatherData.setTemperatureK(element.getMain().getTemp());
            weatherData.setTimestamp((long) element.getDt() * 1000);
            forecastWeatherData.add(weatherData);
        }
        weatherForecast.setWeatherForecast(forecastWeatherData);
        return weatherForecast;
    }


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    static WeatherType extractWeatherType(List<Weather> weatherList) {
        Set<String> weatherStringTypes = new HashSet<>();
        for (Weather weather : weatherList) {
            weatherStringTypes.add(weather.getMain());
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

    public static DatabaseWeatherData fromNetworkToDatabaseData(CurrentWeather netData) {
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
