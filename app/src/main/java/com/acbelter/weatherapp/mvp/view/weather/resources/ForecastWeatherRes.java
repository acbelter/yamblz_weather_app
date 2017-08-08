package com.acbelter.weatherapp.mvp.view.weather.resources;

import android.support.annotation.DrawableRes;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherFavorites;

public class ForecastWeatherRes {

    @DrawableRes
    private int weatherImageResId;

    public ForecastWeatherRes(ForecastWeatherFavorites data) {
        switch (data.getWeatherType()) {
            case SUN:
                weatherImageResId = R.drawable.img_sun;
                break;
            case CLOUDS:
                weatherImageResId = R.drawable.img_clouds;
                break;
            case SNOW:
                weatherImageResId = R.drawable.img_snow;
            case RAIN:
                weatherImageResId = R.drawable.img_rain;
            case STORM:
                weatherImageResId = R.drawable.img_storm;
                break;
        }
    }

    @DrawableRes
    public int getWeatherImageResId() {
        return weatherImageResId;
    }
}
