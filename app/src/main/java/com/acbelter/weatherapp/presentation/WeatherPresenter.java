package com.acbelter.weatherapp.presentation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.acbelter.weatherapp.WeatherUpdateScheduler;
import com.acbelter.weatherapp.WeatherUpdateService;
import com.acbelter.weatherapp.data.repository.PreferencesRepo;
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
    private PreferencesRepo mPrefsRepo;
    private WeatherInteractor mWeatherInteractor;
    private Disposable mCurrentWeatherDisposable;

    private BroadcastReceiver mWeatherUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            WeatherData weatherData =
                    intent.getParcelableExtra(WeatherUpdateService.KEY_WEATHER_DATA);
            long updateTimestamp = intent.getLongExtra(WeatherUpdateService.KEY_WEATHER_UPDATE_TIMESTAMP, 0L);
            getViewState().showWeather(weatherData, updateTimestamp);
        }
    };

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

    public void resume(Context context) {
        IntentFilter filter = new IntentFilter(WeatherUpdateService.ACTION_WEATHER_UPDATE);
        LocalBroadcastManager.getInstance(context).registerReceiver(mWeatherUpdateReceiver, filter);
    }

    public void pause(Context context) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(mWeatherUpdateReceiver);
    }
}
