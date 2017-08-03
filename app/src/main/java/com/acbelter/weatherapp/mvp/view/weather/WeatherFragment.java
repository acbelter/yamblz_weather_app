package com.acbelter.weatherapp.mvp.view.weather;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;
import com.acbelter.weatherapp.mvp.presentation.WeatherPresenter;
import com.acbelter.weatherapp.mvp.view.activity.drawer.DrawerLocker;
import com.acbelter.weatherapp.mvp.view.fragment.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;
import xyz.matteobattilana.library.Common.Constants;

import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.CELSIUS;

public class WeatherFragment extends BaseFragment implements WeatherView {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
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

    private Unbinder mUnbinder;

    @Inject
    WeatherPresenter presenter;

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        presenter.onAttach(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        setSwipeLayout();
        setWeatherView();

        return view;
    }

    private void setSwipeLayout() {
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.updateWeather());
    }

    private void setWeatherView() {
        weatherView.setWeather(Constants.weatherStatus.SUN)
                .setRainTime(6000)
                .setSnowTime(6000)
                .setRainAngle(20)
                .setSnowAngle(20)
                .setRainParticles(25)
                .setSnowParticles(25)
                .setFPS(60)
                .setOrientationMode(Constants.orientationStatus.ENABLE);
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.getWeather();
    }

    @Override
    public void onPause() {
        super.onPause();

        weatherView.cancelAnimation();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        App.getInstance().releaseActivityComponent();
        Timber.d("Remove weather component");
        mUnbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.onDetach();
    }

    @Override
    protected void setTitle() {
        getActivity().setTitle(R.string.weather);
    }

    @Override
    protected void setDrawableEnabled() {
        ((DrawerLocker) getActivity()).setDrawerEnable(true);
    }

    @Override
    protected void inject() {
        App.getInstance().plusActivityComponent().inject(this);
    }

    @Override
    public void showWeatherLoading() {
        if (swipeRefreshLayout.isRefreshing()) {
            return;
        }
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showWeather(FullWeatherModel weatherData) {
        swipeRefreshLayout.setRefreshing(false);
        WeatherRes newWeatherRes = new WeatherRes(weatherData.getWeatherData());
        setWeatherTextColor(newWeatherRes.getTextColorResId());
        String temperatureStr = getCurrentTemperatureString(weatherData);
        tvTemperature.setText(temperatureStr);
        String temperatureMetric = convertMetricToString(weatherData.getWeatherData().getTemperatureMetric());
        tvMetric.setText(temperatureMetric);
        tvCity.setText(weatherData.getCityName());
        contentLayout.setBackgroundColor(
                ContextCompat.getColor(getContext(), newWeatherRes.getBackgroundColorResId()));
        weatherImage.setImageResource(newWeatherRes.getWeatherImageResId());
        weatherView.setWeather(newWeatherRes.getWeatherStatus());
        weatherView.startAnimation();
    }

    private String getCurrentTemperatureString(FullWeatherModel fullWeatherModel) {
        int temperature = fullWeatherModel.getWeatherData().getTemperature();
        return String.valueOf(temperature);
    }

    private String convertMetricToString(TemperatureMetric metric) {
        if (metric == CELSIUS)
            return getString(R.string.celsius);
        else
            return getString(R.string.fahrenheit);
    }

    private void setWeatherTextColor(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(getContext(), colorRes);
        tvTemperature.setTextColor(color);
        tvMetric.setTextColor(color);
        tvCity.setTextColor(color);
    }

    @Override
    public void showError() {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar errorSnackbar =
                Snackbar.make(contentLayout, R.string.text_weather_error, Snackbar.LENGTH_LONG);
        errorSnackbar.setAction(R.string.ok, v -> {
        });
        errorSnackbar.show();
    }
}
