package com.acbelter.weatherapp.presentation;

import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.ui.weather.WeatherView;
import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import timber.log.Timber;

@InjectViewState
public class WeatherPresenter extends BasePresenter<WeatherView> {

    private WeatherInteractor mWeatherInteractor;

    @Inject
    public WeatherPresenter(WeatherInteractor weatherInteractor) {
        mWeatherInteractor = weatherInteractor;
    }

    public void getCachedWeather() {
    }

    public void updateWeather() {
//        String city = mPrefsRepo.getCurrentCity();

//        if (city == null) {
//            getViewState().showError();
//            return;
//        }
//
//        WeatherParams params = new WeatherParams(city);
//
//        unsubscribeOnDetach(mWeatherInteractor.getCurrentWeather(params)
//                .subscribe(
//                        weatherData -> {
//                            Timber.d("getCurrentWeather->onNext()");
//                            mPrefsRepo.setLastWeatherData(weatherData);
//                            long updateTimestamp = System.currentTimeMillis();
//                            mPrefsRepo.setLastUpdateTimestamp(updateTimestamp);
//                            getViewState().showWeather(weatherData, updateTimestamp);
//                        },
//                        error -> {
//                            Timber.d("getCurrentWeather->onError(): %s", error.toString());
//                            getViewState().showError();
//                        },
//                        () -> {
//                            Timber.d("getCurrentWeather->onComplete()");
//                        },
//                        disposable -> {
//                            Timber.d("getCurrentWeather->onSubscribe()");
//                            getViewState().showWeatherLoading();
//                        }
//                ));
    }

}
