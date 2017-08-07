package com.acbelter.weatherapp.mvp.view.weather;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherFavorites;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.matteobattilana.library.Common.Constants;

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_CURRENT = 0;
    private static final int VIEW_TYPE_FORECAST = 1;

    private FullWeatherModel fullWeatherModel;
    private Context context;

    public WeatherAdapter(Context context, FullWeatherModel fullWeatherModel) {
        this.context = context;
        this.fullWeatherModel = fullWeatherModel;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_CURRENT) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_current_weather, parent, false);
            return new CurrentWeatherViewHolder(v);
        } else if (viewType == VIEW_TYPE_FORECAST) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast_weather, parent, false);
            return new ForecastWeatherViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CurrentWeatherViewHolder) {
            CurrentWeatherViewHolder headerHolder = (CurrentWeatherViewHolder) holder;
            headerHolder.bind(context, fullWeatherModel.getCurrentWeatherFavorites());
        } else if (holder instanceof ForecastWeatherViewHolder) {
            ForecastWeatherFavorites forecastElement = getItem(position - 1);
            ForecastWeatherViewHolder genericViewHolder = (ForecastWeatherViewHolder) holder;
            genericViewHolder.tvHighTemp.setText(String.valueOf(forecastElement.getMaxTemp()));
            genericViewHolder.tvLowTemp.setText(String.valueOf(forecastElement.getMinTemp()));
            genericViewHolder.tvDate.setText(forecastElement.getDate());
        }
    }

    private ForecastWeatherFavorites getItem(int position) {
        return fullWeatherModel.getForrecast().get(position);
    }


    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return VIEW_TYPE_CURRENT;
        }
        return VIEW_TYPE_FORECAST;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }


    @Override
    public int getItemCount() {
        return fullWeatherModel.getForrecast().size() + 1;
    }

    public static class CurrentWeatherViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.content_layout)
        ViewGroup contentLayout;
        @BindView(R.id.temperature_text)
        TextView tvTemperature;
        @BindView(R.id.units_text)
        TextView tvMetric;
        @BindView(R.id.location_text)
        TextView tvCity;
        @BindView(R.id.weather_view)
        xyz.matteobattilana.library.WeatherView weatherView;
        @BindView(R.id.weather_image)
        ImageView weatherImage;

        public CurrentWeatherViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(Context context, CurrentWeatherFavorites weather) {

            setWeatherView(context, weather);
            tvCity.setText(weather.getCityData().getShortName());
        }

        private void setWeatherTextColor(Context context, @ColorRes int colorRes) {
            int color = ContextCompat.getColor(context, colorRes);
            tvTemperature.setTextColor(color);
            tvMetric.setTextColor(color);
            tvCity.setTextColor(color);
        }

        private void setWeatherView(Context context, CurrentWeatherFavorites weather) {
            WeatherRes newWeatherRes = new WeatherRes(weather);
            setWeatherTextColor(context, newWeatherRes.getTextColorResId());
            contentLayout.setBackgroundColor(
                    ContextCompat.getColor(context, newWeatherRes.getBackgroundColorResId()));
            weatherView.setWeather(Constants.weatherStatus.SUN)
                    .setRainTime(6000)
                    .setSnowTime(6000)
                    .setRainAngle(20)
                    .setSnowAngle(20)
                    .setRainParticles(25)
                    .setSnowParticles(25)
                    .setFPS(60)
                    .setOrientationMode(Constants.orientationStatus.ENABLE);
            weatherImage.setImageResource(newWeatherRes.getWeatherImageResId());
            weatherView.setWeather(newWeatherRes.getWeatherStatus());
            weatherView.startAnimation();
        }
    }

    public static class ForecastWeatherViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.highTemp)
        TextView tvHighTemp;

        @BindView(R.id.lowTemp)
        TextView tvLowTemp;

        @BindView(R.id.tvDate)
        TextView tvDate;

        public ForecastWeatherViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
