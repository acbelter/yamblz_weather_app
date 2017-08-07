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
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.matteobattilana.library.Common.Constants;

import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.CELSIUS;

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
            ForecastWeatherViewHolder forecastViewHolder = (ForecastWeatherViewHolder) holder;
            forecastViewHolder.bind(forecastElement);
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
        @BindView(R.id.tvTemp)
        TextView tvTemperature;
        @BindView(R.id.tvMetric)
        TextView tvMetric;
        @BindView(R.id.tvLocation)
        TextView tvCity;
        @BindView(R.id.tvHumidity)
        TextView tvHumidity;
        @BindView(R.id.tvWind)
        TextView tvWind;
        @BindView(R.id.tvms)
        TextView tvMs;
        @BindView(R.id.tvDescription)
        TextView tvDescription;
        @BindView(R.id.tvPressure)
        TextView tvPressure;
        @BindView(R.id.tvmm)
        TextView tvMm;
        @BindView(R.id.tvPressureText)
        TextView tvPressureText;
        @BindView(R.id.tvHumidityText)
        TextView getTvHumidityText;
        @BindView(R.id.tvWindText)
        TextView tvWindText;
        @BindView(R.id.imageViewCurrent)
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
            tvTemperature.setText(String.valueOf(weather.getTemperature()));
            tvMetric.setText(convertMetricToString(weather.getTemperatureMetric(), context));
            String humidity = String.valueOf(weather.getHumidity()) + " %";
            tvHumidity.setText(humidity);
            tvWind.setText(String.valueOf(weather.getWindSpeed()));
            tvDescription.setText(weather.getDescription());
            tvPressure.setText(String.valueOf(weather.getPressure()));

        }

        private String convertMetricToString(TemperatureMetric metric, Context context) {
            if (metric == CELSIUS)
                return context.getString(R.string.celsius);
            else
                return context.getString(R.string.fahrenheit);
        }

        private void setWeatherTextColor(Context context, @ColorRes int colorRes) {
            int color = ContextCompat.getColor(context, colorRes);
            tvTemperature.setTextColor(color);
            tvMetric.setTextColor(color);
            tvCity.setTextColor(color);
            tvHumidity.setTextColor(color);
            tvWind.setTextColor(color);
            tvDescription.setTextColor(color);
            tvPressure.setTextColor(color);
            tvMm.setTextColor(color);
            tvMs.setTextColor(color);
            tvPressureText.setTextColor(color);
            getTvHumidityText.setTextColor(color);
            tvWindText.setTextColor(color);
        }

        private void setWeatherView(Context context, CurrentWeatherFavorites weather) {
            CurrentWeatherRes newCurrentWeatherRes = new CurrentWeatherRes(weather);
            setWeatherTextColor(context, newCurrentWeatherRes.getTextColorResId());
            contentLayout.setBackgroundColor(
                    ContextCompat.getColor(context, newCurrentWeatherRes.getBackgroundColorResId()));
            weatherView.setWeather(Constants.weatherStatus.SUN)
                    .setRainTime(6000)
                    .setSnowTime(6000)
                    .setRainAngle(20)
                    .setSnowAngle(20)
                    .setRainParticles(25)
                    .setSnowParticles(25)
                    .setFPS(60)
                    .setOrientationMode(Constants.orientationStatus.ENABLE);
            weatherImage.setImageResource(newCurrentWeatherRes.getWeatherImageResId());
            weatherView.setWeather(newCurrentWeatherRes.getWeatherStatus());
            weatherView.startAnimation();
        }
    }

    public static class ForecastWeatherViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvForecastHigh)
        TextView tvMaxTemp;
        @BindView(R.id.tvForecastLow)
        TextView tvMinTemp;
        @BindView(R.id.tvForecastDate)
        TextView tvDate;
        @BindView(R.id.forecastImageView)
        ImageView iwForecast;

        public ForecastWeatherViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(ForecastWeatherFavorites forecastElement) {
            setWeatherView(forecastElement);
            tvMaxTemp.setText(String.valueOf(forecastElement.getMaxTemp()));
            tvMinTemp.setText(String.valueOf(forecastElement.getMinTemp()));
            tvDate.setText(forecastElement.getDate());
        }

        private void setWeatherView(ForecastWeatherFavorites weather) {
            ForecastWeatherRes forecastWeatherRes = new ForecastWeatherRes(weather);
            iwForecast.setImageResource(forecastWeatherRes.getWeatherImageResId());
        }

    }
}
