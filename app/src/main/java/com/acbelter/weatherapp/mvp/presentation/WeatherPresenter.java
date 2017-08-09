package com.acbelter.weatherapp.mvp.presentation;

import android.support.annotation.NonNull;

import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.mvp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.mvp.view.weather.WeatherView;

import javax.inject.Inject;

public class WeatherPresenter extends BasePresenter<WeatherView> {

    @NonNull
    private final WeatherInteractor weatherInteractor;

    @Inject
    public WeatherPresenter(@NonNull WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
    }

    public void getWeather() {
        if (getView() == null)
            return;
        unSubscribeOnDetach(weatherInteractor.getWeather()
                .subscribe(fullWeatherModel -> getView().showWeather(fullWeatherModel)
                        , throwable -> getView().showError()));
    }

    public void updateWeather() {
        if (getView() == null)
            return;
        getView().startLoading();
        unSubscribeOnDetach(weatherInteractor.updateWeather()
                .subscribe(weatherData -> {
                            getView().showWeather(weatherData);

                        }
                        , throwable -> {
                            getView().stopLoading();
                            getView().showError();
                        }));
    }
}
