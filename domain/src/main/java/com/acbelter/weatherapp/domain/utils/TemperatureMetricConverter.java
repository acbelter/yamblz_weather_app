package com.acbelter.weatherapp.domain.utils;

import android.support.annotation.NonNull;

public class TemperatureMetricConverter {

    @NonNull
    public static int getSupportedTemperature(@NonNull double temperatureInKelvin, @NonNull TemperatureMetric metric) {
        switch (metric) {
            case CELSIUS:
                return (int) Math.round(temperatureInKelvin - 273.15);
            case FAHRENHEIT:
                return (int) Math.round(1.8 * (temperatureInKelvin - 273.15) + 32);
            default:
                return (int) Math.round(temperatureInKelvin);
        }
    }
}
