package com.acbelter.weatherapp.mvp.view.weather.resources;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;

public class CurrentWeatherRes {
    @DrawableRes
    private int weatherImageResId;
    @ColorRes
    private int backgroundColorResId;
    @ColorRes
    private int textColorResId;

    public CurrentWeatherRes(CurrentWeatherFavorites data) {
        switch (data.getWeatherType()) {
            case SUN:
                if (data.isDay()) {
                    weatherImageResId = R.drawable.img_sun;
                } else {
                    weatherImageResId = R.drawable.img_night;
                }
                backgroundColorResId = R.color.colorBgWeatherSun;
                textColorResId = R.color.colorTextWeatherLight;
                break;
            case CLOUDS:
                if (data.isDay()) {
                    weatherImageResId = R.drawable.img_clouds;
                } else {
                    weatherImageResId = R.drawable.img_clouds_night;
                }
                backgroundColorResId = R.color.colorBgWeatherClouds;
                textColorResId = R.color.colorTextWeatherDark;
                break;
            case SNOW:
                weatherImageResId = R.drawable.img_snow;
                backgroundColorResId = R.color.colorBgWeatherSnow;
                textColorResId = R.color.colorTextWeatherLight;
                break;
            case RAIN:
                weatherImageResId = R.drawable.img_rain;
                backgroundColorResId = R.color.colorBgWeatherRain;
                textColorResId = R.color.colorTextWeatherLight;
                break;
            case STORM:
                weatherImageResId = R.drawable.img_storm;
                backgroundColorResId = R.color.colorBgWeatherStorm;
                textColorResId = R.color.colorTextWeatherLight;
                break;
        }

        if (data.isNight()) {
            backgroundColorResId = R.color.colorBgWeatherNight;
            textColorResId = R.color.colorTextWeatherLight;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CurrentWeatherRes that = (CurrentWeatherRes) o;
        return ((weatherImageResId != that.weatherImageResId)
                || (backgroundColorResId != that.backgroundColorResId) || (textColorResId != that.textColorResId));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + backgroundColorResId;
        result = 31 * result + textColorResId;
        result = 31 * result + weatherImageResId;
        return result;
    }

    @DrawableRes
    public int getWeatherImageResId() {
        return weatherImageResId;
    }

    @ColorRes
    public int getBackgroundColorResId() {
        return backgroundColorResId;
    }

    @ColorRes
    public int getTextColorResId() {
        return textColorResId;
    }
}
