package com.acbelter.weatherapp.ui.weather;

import java.security.SecureRandom;

import xyz.matteobattilana.library.Common.Constants;

public final class WeatherUtils {
    // FIXME For testing
    public static WeatherStateRes getRandomStateRes() {
        int result = new SecureRandom().nextInt(WeatherStateRes.class.getEnumConstants().length);
        return WeatherStateRes.class.getEnumConstants()[result];
    }

    public static Constants.weatherStatus getWeatherStatus(WeatherStateRes weatherStateRes) {
        switch (weatherStateRes) {
            case HEAT:
                return Constants.weatherStatus.SUN;
            case SUN:
                return Constants.weatherStatus.SUN;
            case CLOUDY:
                return Constants.weatherStatus.SUN;
            case RAIN_LIGHT:
                return Constants.weatherStatus.RAIN;
            case RAIN_HEAVY:
                return Constants.weatherStatus.RAIN;
            case STORM:
                return Constants.weatherStatus.RAIN;
            case SNOW:
                return Constants.weatherStatus.SNOW;
            case NIGHT:
                return Constants.weatherStatus.SUN;
            default:
                return Constants.weatherStatus.SUN;
        }
    }
}
