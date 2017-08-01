package com.acbelter.weatherapp.presentation;

import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.ui.weather.WeatherView;
import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

@InjectViewState
public class WeatherPresenter extends BasePresenter<WeatherView> {

    private WeatherInteractor weatherInteractor;

    @Inject
    public WeatherPresenter(WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
    }

    public void getCachedWeather() {
        unsubscribeOnDetach(weatherInteractor.getWeather()
                .subscribe(weatherData -> getViewState().showWeather(weatherData)));
    }
}
