package com.acbelter.weatherapp.ui.weather;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

import com.acbelter.weatherapp.R;

public enum WeatherStateRes {
    HEAT(R.drawable.img_heat, R.color.colorBgWeatherHeat, R.color.colorTextWeatherDark),
    SUN(R.drawable.img_sun, R.color.colorBgWeatherSun, R.color.colorTextWeatherLight),
    CLOUDY(R.drawable.img_cloudy, R.color.colorBgWeatherCloudy, R.color.colorTextWeatherDark),
    RAIN_LIGHT(R.drawable.img_rain_light, R.color.colorBgWeatherRainLight, R.color.colorTextWeatherDark),
    RAIN_HEAVY(R.drawable.img_rain_heavy, R.color.colorBgWeatherRainHeavy, R.color.colorTextWeatherLight),
    STORM(R.drawable.img_storm, R.color.colorBgWeatherStorm, R.color.colorTextWeatherLight),
    SNOW(R.drawable.img_snow, R.color.colorBgWeatherSnow, R.color.colorTextWeatherLight),
    NIGHT(R.drawable.img_night, R.color.colorBgWeatherNight, R.color.colorTextWeatherLight);

    @DrawableRes
    private final int mWeatherImageResId;
    @ColorRes
    private final int mBackgroundColorResId;
    @ColorRes
    private final int mTextColorResId;

    WeatherStateRes(@DrawableRes int weatherImgResId,
                    @ColorRes int bgColorResId,
                    @ColorRes int textColorResId) {
        mWeatherImageResId = weatherImgResId;
        mBackgroundColorResId = bgColorResId;
        mTextColorResId = textColorResId;
    }

    @DrawableRes
    public int getWeatherImageResId() {
        return mWeatherImageResId;
    }

    @ColorRes
    public int getBackgroundColorResId() {
        return mBackgroundColorResId;
    }

    public int getBackgroundColor(Context context) {
        return ContextCompat.getColor(context, mBackgroundColorResId);
    }

    @ColorRes
    public int getTextColorResId() {
        return mTextColorResId;
    }

    public int getTextColor(Context context) {
        return ContextCompat.getColor(context, mTextColorResId);
    }
}
