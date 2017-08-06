package com.acbelter.weatherapp.data.repository.weather;

import android.support.annotation.VisibleForTesting;

import com.acbelter.weatherapp.data.weathermodel.common.Weather;
import com.acbelter.weatherapp.data.weathermodel.forecast.ForecastElement;
import com.acbelter.weatherapp.data.weathermodel.forecast.ForecastWeather;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherForecast;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherType;
import com.acbelter.weatherapp.domain.utils.TemperatureMetricConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WeatherDataConverter {

    private WeatherDataConverter() {
    }

    public static CurrentWeatherData fromNWWeatherDataToCurrentWeatherData(com.acbelter.weatherapp.data.weathermodel.currentweather.CurrentWeather currentWeather, WeatherParams weatherParams) {
        if (currentWeather == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        if (currentWeather.getCod() != 200) {
            return null;
        }

        CurrentWeatherData weatherData = new CurrentWeatherData();
        weatherData.setCityData(weatherParams.getCityData());
        int temperature = TemperatureMetricConverter.getSupportedTemperature(currentWeather.getMain().getTemp(), weatherParams.getMetric());
        weatherData.setTemperature(temperature);
        weatherData.setTemperatureMetric(weatherParams.getMetric());
        weatherData.setWeatherType(extractWeatherType(currentWeather.getWeather()));
        weatherData.setTimestamp((long) currentWeather.getDt() * 1000);
        weatherData.setSunriseTimestamp((long) currentWeather.getSys().getSunrise() * 1000);
        weatherData.setSunsetTimestamp((long) currentWeather.getSys().getSunset() * 1000);
        return weatherData;
    }

    public static List<WeatherForecast> fromNWWeatherDataToForecastWeatherData(ForecastWeather forecastNW, WeatherParams weatherParams) {
        if (forecastNW == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        if (Integer.valueOf(forecastNW.getCod()) != 200) {
            return null;
        }


        List<ForecastElement> forecastListNW = forecastNW.getForecastElement();
        List<WeatherForecast> weatherForecasts = new ArrayList<>();
        for (ForecastElement element : forecastListNW) {
            WeatherForecast weatherForecast = fromForecastElementToWeatherForecast(element, weatherParams);
            weatherForecasts.add(weatherForecast);
        }
        return weatherForecasts;
    }

    private static WeatherForecast fromForecastElementToWeatherForecast(ForecastElement forecastElement, WeatherParams weatherParams) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        long curTime = forecastElement.getDt() * 1000L;
        date.setTime(curTime);
        String dateStr = df.format(date);
        int lowTemp = TemperatureMetricConverter.getSupportedTemperature(forecastElement.getTemp().getMin(), weatherParams.getMetric());
        int highTemp = TemperatureMetricConverter.getSupportedTemperature(forecastElement.getTemp().getMax(), weatherParams.getMetric());
        return new WeatherForecast(dateStr, lowTemp, highTemp);
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
}
