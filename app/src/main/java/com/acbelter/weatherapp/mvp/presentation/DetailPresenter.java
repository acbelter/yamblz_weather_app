package com.acbelter.weatherapp.mvp.presentation;

import android.support.annotation.NonNull;

import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.mvp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.mvp.view.weather.details.DetailView;

import javax.inject.Inject;

public class DetailPresenter extends BasePresenter<DetailView> {

    @NonNull
    private final WeatherInteractor weatherInteractor;

    @Inject
    public DetailPresenter(@NonNull WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
    }

    public void getWeather() {
        if (getView() == null)
            return;
        unSubscribeOnDetach(weatherInteractor.getWeather()
                .subscribe(fullWeatherModel -> getView().showWeather(fullWeatherModel)));
    }
}
