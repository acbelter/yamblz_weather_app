package com.acbelter.weatherapp.data.repository.weather;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.acbelter.weatherapp.data.weathermodel.common.Weather;
import com.acbelter.weatherapp.data.weathermodel.currentweather.CurrentWeather;
import com.acbelter.weatherapp.data.weathermodel.forecast.ForecastElement;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherElement;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherType;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

class WeatherDataConverter {

    private WeatherDataConverter() {
    }

    static
    @NonNull
    CurrentWeatherFavorites updateCurrentWeatherMetric(@Nullable CurrentWeatherFavorites currentWeatherFavorites, @Nullable TemperatureMetric temperatureMetric) {

        if (currentWeatherFavorites == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        currentWeatherFavorites.setTemperatureMetric(temperatureMetric);
        return currentWeatherFavorites;
    }

    static
    @NonNull
    ForecastWeatherElement updateForecastWeatherMetric(@Nullable ForecastWeatherElement forecastWeatherElement, @Nullable TemperatureMetric temperatureMetric) {
        if (forecastWeatherElement == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        forecastWeatherElement.setTemperatureMetric(temperatureMetric);
        return forecastWeatherElement;
    }

    static
    @Nullable
    CurrentWeatherFavorites fromNWWeatherDataToCurrentWeatherData(@Nullable CurrentWeather currentWeather, @NonNull WeatherParams weatherParams) {

        if (currentWeather == null) {
            throw new IllegalArgumentException("Converted object must be not null");
        }

        if (currentWeather.getCod() != 200) {
            return null;
        }

        double temperature = currentWeather.getMain().getTemp();
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
                .minTemp(currentWeather.getMain().getTempMin())
                .maxTemp(currentWeather.getMain().getTempMax())
                .build();
    }

    static
    @NonNull
    ForecastWeatherElement fromForecastElementToWeatherForecast(@NonNull ForecastElement forecastElement, @NonNull WeatherParams weatherParams) {
        DateFormat df = new SimpleDateFormat("dd MMMM", Locale.getDefault());
        Date date = new Date();
        long curTime = forecastElement.getDt() * 1000L;
        date.setTime(curTime);
        String dateStr = df.format(date);
        double lowTemp = forecastElement.getTemp().getMin();
        double highTemp = forecastElement.getTemp().getMax();
        TemperatureMetric temperatureMetric = weatherParams.getMetric();
        return new ForecastWeatherElement.Builder(dateStr, lowTemp, highTemp, temperatureMetric)
                .weatherType(extractWeatherType(forecastElement.getWeather()))
                .pressure((int)Math.round(forecastElement.getPressure()))
                .humidity(forecastElement.getHumidity())
                .description(forecastElement.getWeather().get(0).getDescription())
                .windSpeed((int)Math.round(forecastElement.getSpeed()))
                .build();
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    static
    @NonNull
    WeatherType extractWeatherType(@Nullable List<Weather> weatherList) {
        Set<String> weatherStringTypes = new HashSet<>();
        if (weatherList == null)
            throw new NullPointerException("Weather list is null");
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
