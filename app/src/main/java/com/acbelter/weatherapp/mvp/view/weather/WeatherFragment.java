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
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.mvp.presentation.WeatherPresenter;
import com.acbelter.weatherapp.mvp.view.activity.drawer.DrawerLocker;
import com.acbelter.weatherapp.mvp.view.fragment.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;
import xyz.matteobattilana.library.Common.Constants;

public class WeatherFragment extends BaseFragment implements WeatherView {

    @Inject
    @InjectPresenter
    WeatherPresenter presenter;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.content_layout)
    ViewGroup contentLayout;
    @BindView(R.id.temperature_text)
    TextView temperatureText;
    @BindView(R.id.units_text)
    TextView unitsText;
    @BindView(R.id.location_text)
    TextView locationText;
    @BindView(R.id.weather_view)
    xyz.matteobattilana.library.WeatherView weatherView;
    @BindView(R.id.weather_image)
    ImageView weatherImage;
    private Unbinder mUnbinder;

    @ProvidePresenter
    public WeatherPresenter provideWeatherPresenter() {
        return presenter;
    }

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
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
        getActivity().setTitle(R.string.settings);
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
    public void showWeather(WeatherData weatherData) {
        swipeRefreshLayout.setRefreshing(false);
        WeatherRes newWeatherRes = new WeatherRes(weatherData);
        setWeatherTextColor(newWeatherRes.getTextColorResId());
        temperatureText.setText(String.valueOf(Math.round(weatherData.getTemperatureC())));
        unitsText.setText(R.string.celsius);
        locationText.setText(weatherData.getCity());
        contentLayout.setBackgroundColor(
                ContextCompat.getColor(getContext(), newWeatherRes.getBackgroundColorResId()));
        weatherImage.setImageResource(newWeatherRes.getWeatherImageResId());
        weatherView.setWeather(newWeatherRes.getWeatherStatus());
        weatherView.startAnimation();
    }

    private void setWeatherTextColor(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(getContext(), colorRes);
        temperatureText.setTextColor(color);
        unitsText.setTextColor(color);
        locationText.setTextColor(color);
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

    public static String tag() {
        return WeatherFragment.class.getSimpleName();
    }
}
