package com.acbelter.weatherapp.mvp.view.weather;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;

import xyz.matteobattilana.library.Common.Constants;

public class CurrentWeatherRes {
    @DrawableRes
    private int weatherImageResId;
    @ColorRes
    private int backgroundColorResId;
    @ColorRes
    private int textColorResId;
    private Constants.weatherStatus mWeatherStatus;

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
                mWeatherStatus = Constants.weatherStatus.SUN;
                break;
            case CLOUDS:
                if (data.isDay()) {
                    weatherImageResId = R.drawable.img_clouds;
                } else {
                    weatherImageResId = R.drawable.img_clouds_night;
                }
                backgroundColorResId = R.color.colorBgWeatherClouds;
                textColorResId = R.color.colorTextWeatherDark;
                mWeatherStatus = Constants.weatherStatus.SUN;
                break;
            case SNOW:
                weatherImageResId = R.drawable.img_snow;
                backgroundColorResId = R.color.colorBgWeatherSnow;
                textColorResId = R.color.colorTextWeatherLight;
                mWeatherStatus = Constants.weatherStatus.SNOW;
                break;
            case RAIN:
                weatherImageResId = R.drawable.img_rain;
                backgroundColorResId = R.color.colorBgWeatherRain;
                textColorResId = R.color.colorTextWeatherLight;
                mWeatherStatus = Constants.weatherStatus.RAIN;
                break;
            case STORM:
                weatherImageResId = R.drawable.img_storm;
                backgroundColorResId = R.color.colorBgWeatherStorm;
                textColorResId = R.color.colorTextWeatherLight;
                mWeatherStatus = Constants.weatherStatus.RAIN;
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
        if (weatherImageResId != that.weatherImageResId) {
            return false;
        }
        if (backgroundColorResId != that.backgroundColorResId) {
            return false;
        }
        if (textColorResId != that.textColorResId) {
            return false;
        }
        return mWeatherStatus == that.mWeatherStatus;
    }

    @Override
    public int hashCode() {
        int result = weatherImageResId;
        result = 31 * result + backgroundColorResId;
        result = 31 * result + textColorResId;
        result = 31 * result + mWeatherStatus.hashCode();
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

    public Constants.weatherStatus getWeatherStatus() {
        return mWeatherStatus;
    }
}
