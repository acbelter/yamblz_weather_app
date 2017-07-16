package com.acbelter.weatherapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.WeatherParams;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class WeatherUpdateReceiver extends BroadcastReceiver {
    public static final String ACTION_WEATHER_UPDATE =
            "com.acbelter.weatherapp.ACTION_WEATHER_UPDATE";
    public static String KEY_WEATHER_DATA =
            "com.acbelter.weatherapp.KEY_WEATHER_DATA";

    @Inject
    PreferencesStorage mPrefsStorage;
    @Inject
    WeatherInteractor mWeatherInteractor;
    private Disposable mUpdateWeatherDisposable;

    @Override
    public void onReceive(Context context, Intent intent) {
        App.getComponentManager().addWeatherComponent().inject(this);
        Timber.d("Update weather event: " + System.currentTimeMillis());

        if (mUpdateWeatherDisposable != null) {
            return;
        }

        String action = intent.getAction();
        if ("android.intent.action.BOOT_COMPLETED".equals(action) ||
                "android.intent.action.ACTION_MY_PACKAGE_REPLACED".equals(action)) {
            WeatherUpdateScheduler.restartWeatherUpdates(context, mPrefsStorage.getUpdateInterval());
        } else {
            updateWeather(context);
        }

        App.getComponentManager().removeWeatherComponent();
    }

    private void updateWeather(Context context) {
        // FIXME City for testing
        WeatherParams params = new WeatherParams("Moscow");

        mUpdateWeatherDisposable = mWeatherInteractor.getCurrentWeather(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        weatherData -> {
                            Timber.d("updateCurrentWeather->onNext()");
                            mPrefsStorage.setLastWeatherData(weatherData);
                            mPrefsStorage.setLastUpdateTimestamp(System.currentTimeMillis());

                            Intent intent = new Intent(ACTION_WEATHER_UPDATE);
                            intent.putExtra(KEY_WEATHER_DATA, weatherData);
                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                        },
                        error -> {
                            Timber.d("updateCurrentWeather->onError(): " + error.toString());
                            clearUpdateWeatherDisposable();
                        },
                        () -> {
                            Timber.d("updateCurrentWeather->onComplete()");
                            clearUpdateWeatherDisposable();
                        },
                        disposable -> {
                            Timber.d("updateCurrentWeather->onSubscribe()");
                        }
                );
    }

    private void clearUpdateWeatherDisposable() {
        mUpdateWeatherDisposable = null;
    }
}
