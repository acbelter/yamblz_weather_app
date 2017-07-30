package com.acbelter.weatherapp.presentation;

import android.content.Context;

import com.acbelter.weatherapp.WeatherUpdateScheduler;
import com.acbelter.weatherapp.data.repository.preference.PreferencesRepo;
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

    private PreferencesRepo mPrefsRepo;
    private WeatherInteractor mWeatherInteractor;

    private WeatherData mWeatherData;

    @Inject
    public WeatherPresenter(Context context,
                            PreferencesRepo prefsRepo,
                            WeatherInteractor weatherInteractor) {
        mPrefsRepo = prefsRepo;
        mWeatherInteractor = weatherInteractor;
        int updateInterval = prefsRepo.getUpdateInterval();
        if (updateInterval > 0) {
            WeatherUpdateScheduler.startWeatherUpdates(context, updateInterval, false);
        }
    }

    public void setWeatherData(WeatherData weatherData) {
        mWeatherData = weatherData;
    }

    public void getCachedWeather() {
        mWeatherData = mPrefsRepo.getLastWeatherData();
        if (mWeatherData != null)
            getViewState().showWeather(mWeatherData, mPrefsRepo.getLastUpdateTimestamp());
        else
            updateWeather();
    }

    public void updateWeather() {
        String city = mPrefsRepo.getCurrentCity();

        if (city == null) {
            getViewState().showError();
            return;
        }

        WeatherParams params = new WeatherParams(city);

        unsubscribeOnDetach(mWeatherInteractor.getCurrentWeather(params)
                .subscribe(
                        weatherData -> {
                            Timber.d("getCurrentWeather->onNext()");
                            mPrefsRepo.setLastWeatherData(weatherData);
                            long updateTimestamp = System.currentTimeMillis();
                            mPrefsRepo.setLastUpdateTimestamp(updateTimestamp);
                            getViewState().showWeather(weatherData, updateTimestamp);
                        },
                        error -> {
                            Timber.d("getCurrentWeather->onError(): %s", error.toString());
                            getViewState().showError();
                        },
                        () -> {
                            Timber.d("getCurrentWeather->onComplete()");
                        },
                        disposable -> {
                            Timber.d("getCurrentWeather->onSubscribe()");
                            getViewState().showWeatherLoading();
                        }
                ));
    }

    public WeatherData getWeatherData() {
        return mWeatherData;
    }
}
