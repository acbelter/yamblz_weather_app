package com.acbelter.weatherapp.mvp.presentation;

import com.acbelter.weatherapp.domain.interactor.SettingsInteractor;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.mvp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.mvp.view.weather.WeatherView;

import javax.inject.Inject;

public class WeatherPresenter extends BasePresenter<WeatherView> {

    private WeatherInteractor weatherInteractor;
    private SettingsInteractor settingsInteractor;

    @Inject
    public WeatherPresenter(WeatherInteractor weatherInteractor, SettingsInteractor settingsInteractor) {
        this.weatherInteractor = weatherInteractor;
        this.settingsInteractor = settingsInteractor;
    }

    public void getWeather() {
        if (getView() == null)
            return;
        unSubscribeOnDetach(weatherInteractor.getWeather()
                .subscribe(weatherData -> getView().showWeather(weatherData)));
    }

    public void updateWeather() {
        if (getView() == null)
            return;
        unSubscribeOnDetach(weatherInteractor.updateWeather()
                .subscribe(weatherData -> getView().showWeather(weatherData)));
    }
}
