package com.acbelter.weatherapp.ui.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.WeatherData;
import com.acbelter.weatherapp.presentation.WeatherPresenter;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class WeatherFragment extends MvpAppCompatFragment implements WeatherView {
    @Inject
    @InjectPresenter
    WeatherPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getComponentManager().addWeatherComponent().inject(this);
        super.onCreate(savedInstanceState);
        Timber.d("Add weather component");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        App.getComponentManager().removeWeatherComponent();
        Timber.d("Remove weather component");
    }

    @ProvidePresenter
    public WeatherPresenter provideWeatherPresenter() {
        return mPresenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getWeather();
    }

    @Override
    public void showWeatherLoading() {

    }

    @Override
    public void showWeather(List<WeatherData> weatherDataList) {

    }

    @Override
    public void showError() {

    }

    public static String tag() {
        return WeatherFragment.class.getSimpleName();
    }
}
