package com.acbelter.weatherapp.data.repository.weather;

import android.support.annotation.VisibleForTesting;

import com.acbelter.weatherapp.data.weathermodel.common.Weather;
import com.acbelter.weatherapp.data.weathermodel.currentweather.CurrentWeather;
import com.acbelter.weatherapp.data.weathermodel.forecast.ForecastElement;
import com.acbelter.weatherapp.data.weathermodel.forecast.ForecastWeather;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherType;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;
import com.acbelter.weatherapp.domain.utils.TemperatureMetricConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class WeatherDataConverter {

    private WeatherDataConverter() {
    }

    public static CurrentWeatherFavorites fromNWWeatherDataToCurrentWeatherData(CurrentWeather currentWeather, WeatherParams weatherParams) {
        if (currentWeather == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        if (currentWeather.getCod() != 200) {
            return null;
        }

        int temperature = TemperatureMetricConverter
                .getSupportedTemperature(currentWeather.getMain().getTemp(), weatherParams.getMetric());
        CityData cityData = weatherParams.getCityData();
        TemperatureMetric temperatureMetric = weatherParams.getMetric();
        return new CurrentWeatherFavorites.Builder(temperature, cityData, temperatureMetric)
                .weatherType(extractWeatherType(currentWeather.getWeather()))
                .timestamp((long) currentWeather.getDt() * 1000L)
                .sunriseTimestamp((long) currentWeather.getSys().getSunrise() * 1000L)
                .sunsetTimestamp((long) currentWeather.getSys().getSunset() * 1000L)
                .pressure((int) Math.round(currentWeather.getMain().getPressure()))
                .humidity(currentWeather.getMain().getHumidity())
                .description(currentWeather.getWeather().get(0).getDescription())
                .windSpeed((int) Math.round(currentWeather.getWind().getSpeed()))
                .minTemp(TemperatureMetricConverter
                        .getSupportedTemperature(currentWeather.getMain().getTempMin(), weatherParams.getMetric()))
                .maxTemp(TemperatureMetricConverter
                        .getSupportedTemperature(currentWeather.getMain().getTempMax(), weatherParams.getMetric()))
                .build();
    }

    public static List<ForecastWeatherFavorites> fromNWWeatherDataToForecastWeatherData(ForecastWeather forecastNW, WeatherParams weatherParams) {
        if (forecastNW == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        if (Integer.valueOf(forecastNW.getCod()) != 200) {
            return null;
        }


        List<ForecastElement> forecastListNW = forecastNW.getForecastElement();
        List<ForecastWeatherFavorites> forecastWeatherFavoritesList = new ArrayList<>();
        for (ForecastElement element : forecastListNW) {
            ForecastWeatherFavorites forecastWeatherFavorites = fromForecastElementToWeatherForecast(element, weatherParams);
            forecastWeatherFavoritesList.add(forecastWeatherFavorites);
        }
        return forecastWeatherFavoritesList;
    }

    private static ForecastWeatherFavorites fromForecastElementToWeatherForecast(ForecastElement forecastElement, WeatherParams weatherParams) {
        DateFormat df = new SimpleDateFormat("dd MMMM", Locale.getDefault());
        Date date = new Date();
        long curTime = forecastElement.getDt() * 1000L;
        date.setTime(curTime);
        String dateStr = df.format(date);
        int lowTemp = TemperatureMetricConverter.getSupportedTemperature(forecastElement.getTemp().getMin(), weatherParams.getMetric());
        int highTemp = TemperatureMetricConverter.getSupportedTemperature(forecastElement.getTemp().getMax(), weatherParams.getMetric());
        TemperatureMetric temperatureMetric = weatherParams.getMetric();
        return new ForecastWeatherFavorites.Builder(dateStr, lowTemp, highTemp, temperatureMetric)
                .weatherType(extractWeatherType(forecastElement.getWeather()))
                .build();
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
