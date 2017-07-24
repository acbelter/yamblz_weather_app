package com.acbelter.weatherapp.presentation;

import android.content.Context;

import com.acbelter.weatherapp.WeatherUpdateScheduler;
import com.acbelter.weatherapp.data.repository.PreferencesRepo;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.ui.weather.WeatherView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> {
    private PreferencesRepo mPrefsRepo;
    private WeatherInteractor mWeatherInteractor;
    private Disposable mCurrentWeatherDisposable;

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

    public WeatherData getWeatherData() {
        return mWeatherData;
    }

    public void getCurrentWeather(boolean forceRefresh) {
        mWeatherData = mPrefsRepo.getLastWeatherData();
        Timber.d("Update current weather: %s", mWeatherData);

        if (mWeatherData != null && !forceRefresh) {
            getViewState().showWeather(mWeatherData, mPrefsRepo.getLastUpdateTimestamp());
            return;
        }

        if (mCurrentWeatherDisposable != null) {
            // Getting weather already in progress
            return;
        }

        String city = mPrefsRepo.getCurrentCity();
        if (city == null) {
            getViewState().showError();
            return;
        }

        WeatherParams params = new WeatherParams(city);

        mCurrentWeatherDisposable = mWeatherInteractor.getCurrentWeather(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
                            mCurrentWeatherDisposable = null;
                            getViewState().showError();
                        },
                        () -> {
                            Timber.d("getCurrentWeather->onComplete()");
                            mCurrentWeatherDisposable = null;
                        },
                        disposable -> {
                            Timber.d("getCurrentWeather->onSubscribe()");
                            getViewState().showWeatherLoading();
                        }
                );
    }

    public void stopGetCurrentWeatherProcess() {
        if (mCurrentWeatherDisposable != null && !mCurrentWeatherDisposable.isDisposed()) {
            mCurrentWeatherDisposable.dispose();
        }
        mCurrentWeatherDisposable = null;
    }
}
