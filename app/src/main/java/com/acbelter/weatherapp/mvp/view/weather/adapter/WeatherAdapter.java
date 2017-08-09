package com.acbelter.weatherapp.mvp.view.weather.adapter;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.acbelter.weatherapp.domain.utils.TemperatureMetricConverter;
import com.acbelter.weatherapp.mvp.view.weather.resources.CurrentWeatherRes;
import com.acbelter.weatherapp.mvp.view.weather.resources.ForecastWeatherRes;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.CELSIUS;

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_CURRENT = 0;
    private static final int VIEW_TYPE_FORECAST = 1;

    @Nullable
    private FullWeatherModel fullWeatherModel;

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
            if (fullWeatherModel != null)
                headerHolder.bind(fullWeatherModel.getCurrentWeatherFavorites());
        } else if (holder instanceof ForecastWeatherViewHolder) {
            try {
                ForecastWeatherFavorites forecastElement = getItem(position - 1);
                ForecastWeatherViewHolder forecastViewHolder = (ForecastWeatherViewHolder) holder;
                forecastViewHolder.bind(forecastElement);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    private
    @NonNull
    ForecastWeatherFavorites getItem(int position) throws NullPointerException {
        if (fullWeatherModel == null)
            throw new NullPointerException("FullWeatherModel is null");
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
        if (fullWeatherModel == null)
            return 0;
        return fullWeatherModel.getForrecast().size() + 1;
    }

    public void update(@Nullable FullWeatherModel fullWeatherModel) {
        if (fullWeatherModel == null)
            return;
        this.fullWeatherModel = fullWeatherModel;
        notifyDataSetChanged();
    }

    static class CurrentWeatherViewHolder extends RecyclerView.ViewHolder {

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
        @BindView(R.id.weather_image)
        ImageView weatherImage;

        private final Context context;

        CurrentWeatherViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = itemView.getContext();
        }

        void bind(CurrentWeatherFavorites weather) {
            setWeatherView(weather);
            tvCity.setText(weather.getCityData().getShortName());
            int temperature = TemperatureMetricConverter.getSupportedTemperature(weather.getTemperature(), weather.getTemperatureMetric());
            tvTemperature.setText(String.valueOf(temperature));
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

        private void setWeatherView(CurrentWeatherFavorites weather) {
            CurrentWeatherRes newCurrentWeatherRes = new CurrentWeatherRes(weather);
            setWeatherTextColor(context, newCurrentWeatherRes.getTextColorResId());
            contentLayout.setBackgroundColor(
                    ContextCompat.getColor(context, newCurrentWeatherRes.getBackgroundColorResId()));
            weatherImage.setImageResource(newCurrentWeatherRes.getWeatherImageResId());
        }
    }

    static class ForecastWeatherViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvForecastHigh)
        TextView tvMaxTemp;
        @BindView(R.id.tvForecastLow)
        TextView tvMinTemp;
        @BindView(R.id.tvForecastDate)
        TextView tvDate;
        @BindView(R.id.forecastImageView)
        ImageView iwForecast;

        ForecastWeatherViewHolder(final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        void bind(ForecastWeatherFavorites forecastElement) {
            setWeatherView(forecastElement);
            int minTemp = TemperatureMetricConverter.getSupportedTemperature(forecastElement.getMinTemp(), forecastElement.getTemperatureMetric());
            tvMinTemp.setText(String.valueOf(minTemp));
            int maxTemp = TemperatureMetricConverter.getSupportedTemperature(forecastElement.getMaxTemp(), forecastElement.getTemperatureMetric());
            tvMaxTemp.setText(String.valueOf(maxTemp));
            tvDate.setText(forecastElement.getDate());
        }

        private void setWeatherView(ForecastWeatherFavorites weather) {
            ForecastWeatherRes forecastWeatherRes = new ForecastWeatherRes(weather);
            iwForecast.setImageResource(forecastWeatherRes.getWeatherImageResId());
        }

    }
}
