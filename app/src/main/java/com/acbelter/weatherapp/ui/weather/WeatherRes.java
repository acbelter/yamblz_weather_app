package com.acbelter.weatherapp.ui.weather;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.WeatherData;

import xyz.matteobattilana.library.Common.Constants;

public class WeatherRes {
    @DrawableRes
    private int mWeatherImageResId;
    @ColorRes
    private int mBackgroundColorResId;
    @ColorRes
    private int mTextColorResId;
    private Constants.weatherStatus mWeatherStatus;

    public WeatherRes(WeatherData data) {
        switch (data.getWeatherType()) {
            case SUN:
                if (data.isDay()) {
                    mWeatherImageResId = R.drawable.img_sun;
                } else {
                    mWeatherImageResId = R.drawable.img_night;
                }
                mBackgroundColorResId = R.color.colorBgWeatherSun;
                mTextColorResId = R.color.colorTextWeatherLight;
                mWeatherStatus = Constants.weatherStatus.SUN;
                break;
            case CLOUDS:
                if (data.isDay()) {
                    mWeatherImageResId = R.drawable.img_clouds;
                } else {
                    mWeatherImageResId = R.drawable.img_clouds_night;
                }
                mBackgroundColorResId = R.color.colorBgWeatherClouds;
                mTextColorResId = R.color.colorTextWeatherDark;
                mWeatherStatus = Constants.weatherStatus.SUN;
                break;
            case SNOW:
                mWeatherImageResId = R.drawable.img_snow;
                mBackgroundColorResId = R.color.colorBgWeatherSnow;
                mTextColorResId = R.color.colorTextWeatherLight;
                mWeatherStatus = Constants.weatherStatus.SNOW;
                break;
            case RAIN:
                mWeatherImageResId = R.drawable.img_rain;
                mBackgroundColorResId = R.color.colorBgWeatherRain;
                mTextColorResId = R.color.colorTextWeatherLight;
                mWeatherStatus = Constants.weatherStatus.RAIN;
                break;
            case STORM:
                mWeatherImageResId = R.drawable.img_storm;
                mBackgroundColorResId = R.color.colorBgWeatherStorm;
                mTextColorResId = R.color.colorTextWeatherLight;
                mWeatherStatus = Constants.weatherStatus.RAIN;
                break;
        }

        if (data.isNight()) {
            mBackgroundColorResId = R.color.colorBgWeatherNight;
            mTextColorResId = R.color.colorTextWeatherLight;
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

        WeatherRes that = (WeatherRes) o;
        if (mWeatherImageResId != that.mWeatherImageResId) {
            return false;
        }
        if (mBackgroundColorResId != that.mBackgroundColorResId) {
            return false;
        }
        if (mTextColorResId != that.mTextColorResId) {
            return false;
        }
        return mWeatherStatus == that.mWeatherStatus;
    }

    @Override
    public int hashCode() {
        int result = mWeatherImageResId;
        result = 31 * result + mBackgroundColorResId;
        result = 31 * result + mTextColorResId;
        result = 31 * result + mWeatherStatus.hashCode();
        return result;
    }

    @DrawableRes
    public int getWeatherImageResId() {
        return mWeatherImageResId;
    }

    @ColorRes
    public int getBackgroundColorResId() {
        return mBackgroundColorResId;
    }

    @ColorRes
    public int getTextColorResId() {
        return mTextColorResId;
    }

    public Constants.weatherStatus getWeatherStatus() {
        return mWeatherStatus;
    }
}
