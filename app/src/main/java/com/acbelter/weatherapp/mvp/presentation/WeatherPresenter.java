package com.acbelter.weatherapp.mvp.presentation;

import com.acbelter.weatherapp.domain.interactor.SettingsInteractor;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.mvp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.mvp.view.weather.WeatherView;
import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

@InjectViewState
public class WeatherPresenter extends BasePresenter<WeatherView> {

    private WeatherInteractor weatherInteractor;
    private SettingsInteractor settingsInteractor;

    @Inject
    public WeatherPresenter(WeatherInteractor weatherInteractor, SettingsInteractor settingsInteractor) {
        this.weatherInteractor = weatherInteractor;
        this.settingsInteractor = settingsInteractor;
    }

    public void getWeather() {
        unSubscribeOnDetach(weatherInteractor.getWeather()
                .subscribe(weatherData -> getViewState().showWeather(weatherData)));
    }

    public void updateWeather() {
        unSubscribeOnDetach(weatherInteractor.updateWeather()
                .subscribe(weatherData -> getViewState().showWeather(weatherData)));
    }
}
