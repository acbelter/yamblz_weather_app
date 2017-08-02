package com.acbelter.weatherapp.ui.weather;

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
import com.acbelter.weatherapp.presentation.WeatherPresenter;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;
import xyz.matteobattilana.library.Common.Constants;

public class WeatherFragment extends MvpAppCompatFragment implements WeatherView {

    @Inject
    @InjectPresenter
    WeatherPresenter presenter;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.content_layout)
    ViewGroup mContentLayout;
    @BindView(R.id.temperature_text)
    TextView mTemperatureText;
    @BindView(R.id.units_text)
    TextView mUnitsText;
    @BindView(R.id.location_text)
    TextView mLocationText;
    @BindView(R.id.weather_view)
    xyz.matteobattilana.library.WeatherView mWeatherView;
    @BindView(R.id.weather_image)
    ImageView mWeatherImage;
    private Unbinder mUnbinder;
    private long mAnimBgDuration;

    @ProvidePresenter
    public WeatherPresenter provideWeatherPresenter() {
        return presenter;
    }

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getInstance().plusActivityComponent().inject(this);
        super.onCreate(savedInstanceState);
        Timber.d("Add weather component");
        mAnimBgDuration = getContext().getResources().getInteger(R.integer.anim_bg_duration);
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
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(() -> presenter.updateWeather());
    }

    private void setWeatherView() {
        mWeatherView.setWeather(Constants.weatherStatus.SUN)
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

        mWeatherView.cancelAnimation();
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
    public void showWeatherLoading() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            return;
        }
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showWeather(WeatherData weatherData) {
        mSwipeRefreshLayout.setRefreshing(false);
        WeatherRes newWeatherRes = new WeatherRes(weatherData);
        setWeatherTextColor(newWeatherRes.getTextColorResId());
        mTemperatureText.setText(String.valueOf(Math.round(weatherData.getTemperatureC())));
        mUnitsText.setText(R.string.celsius);
        mLocationText.setText(weatherData.getCity());
        mContentLayout.setBackgroundColor(
                ContextCompat.getColor(getContext(), newWeatherRes.getBackgroundColorResId()));
        mWeatherImage.setImageResource(newWeatherRes.getWeatherImageResId());
        mWeatherView.setWeather(newWeatherRes.getWeatherStatus());
        mWeatherView.startAnimation();
    }

    private void setWeatherTextColor(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(getContext(), colorRes);
        mTemperatureText.setTextColor(color);
        mUnitsText.setTextColor(color);
        mLocationText.setTextColor(color);
    }

    @Override
    public void showError() {
        mSwipeRefreshLayout.setRefreshing(false);
        Snackbar errorSnackbar =
                Snackbar.make(mContentLayout, R.string.text_weather_error, Snackbar.LENGTH_LONG);
        errorSnackbar.setAction(R.string.ok, v -> {
        });
        errorSnackbar.show();
    }

    public static String tag() {
        return WeatherFragment.class.getSimpleName();
    }
}
