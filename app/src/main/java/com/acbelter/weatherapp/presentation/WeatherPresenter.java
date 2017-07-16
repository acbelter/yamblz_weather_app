package com.acbelter.weatherapp.presentation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.acbelter.weatherapp.PreferencesStorage;
import com.acbelter.weatherapp.WeatherUpdateReceiver;
import com.acbelter.weatherapp.WeatherUpdateScheduler;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.WeatherData;
import com.acbelter.weatherapp.domain.model.WeatherParams;
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
    private PreferencesStorage mPrefsStorage;
    private WeatherInteractor mWeatherInteractor;
    private Disposable mCurrentWeatherDisposable;

    private BroadcastReceiver mWeatherUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            WeatherData weatherData =
                    intent.getParcelableExtra(WeatherUpdateReceiver.KEY_WEATHER_DATA);
            getViewState().showWeather(weatherData);
        }
    };

    private WeatherData mWeatherData;

    @Inject
    public WeatherPresenter(Context context,
                            PreferencesStorage prefsStorage,
                            WeatherInteractor weatherInteractor) {
        mPrefsStorage = prefsStorage;
        mWeatherInteractor = weatherInteractor;
        int updateInterval = prefsStorage.getUpdateInterval();
        if (updateInterval > 0) {
            WeatherUpdateScheduler.restartWeatherUpdates(context, updateInterval);
        }
    }

    public void setWeatherData(WeatherData weatherData) {
        mWeatherData = weatherData;
    }

    public WeatherData getWeatherData() {
        return mWeatherData;
    }

    public void getCurrentWeather(boolean forceRefresh) {
        mWeatherData = mPrefsStorage.getLastWeatherData();

        if (mWeatherData != null && !forceRefresh) {
            getViewState().showWeather(mWeatherData);
            return;
        }

        if (mCurrentWeatherDisposable != null) {
            // Getting weather already in progress
            return;
        }

        // FIXME City for testing
        WeatherParams params = new WeatherParams("Moscow");

        mCurrentWeatherDisposable = mWeatherInteractor.getCurrentWeather(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        weatherData -> {
                            Timber.d("getCurrentWeather->onNext()");
                            mPrefsStorage.setLastWeatherData(weatherData);
                            mPrefsStorage.setLastUpdateTimestamp(System.currentTimeMillis());
                            getViewState().showWeather(weatherData);
                        },
                        error -> {
                            Timber.d("getCurrentWeather->onError(): " + error.toString());
                            getViewState().showError();
                            clearGetWeatherDisposable();
                        },
                        () -> {
                            Timber.d("getCurrentWeather->onComplete()");
                            clearGetWeatherDisposable();
                        },
                        disposable -> {
                            Timber.d("getCurrentWeather->onSubscribe()");
                            getViewState().showWeatherLoading();
                        }
                );
    }

    private void clearGetWeatherDisposable() {
        mCurrentWeatherDisposable = null;
    }

    public void stopGetCurrentWeatherProcess() {
        if (mCurrentWeatherDisposable != null && !mCurrentWeatherDisposable.isDisposed()) {
            mCurrentWeatherDisposable.dispose();
        }
        mCurrentWeatherDisposable = null;
    }

    public void resume(Context context) {
        IntentFilter filter = new IntentFilter(WeatherUpdateReceiver.ACTION_WEATHER_UPDATE);
        LocalBroadcastManager.getInstance(context).registerReceiver(mWeatherUpdateReceiver, filter);
    }

    public void pause(Context context) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(mWeatherUpdateReceiver);
    }
}
