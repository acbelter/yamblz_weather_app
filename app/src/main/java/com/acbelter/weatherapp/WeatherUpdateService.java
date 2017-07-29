package com.acbelter.weatherapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.acbelter.weatherapp.data.repository.preference.PreferencesRepo;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class WeatherUpdateService extends Service {
    public static final String ACTION_WEATHER_UPDATE =
            "com.acbelter.weatherapp.ACTION_WEATHER_UPDATE";
    public static String KEY_WEATHER_DATA =
            "com.acbelter.weatherapp.KEY_WEATHER_DATA";
    public static String KEY_WEATHER_UPDATE_TIMESTAMP =
            "com.acbelter.weatherapp.KEY_WEATHER_UPDATE_TIMESTAMP";

    @Inject
    WeatherInteractor mWeatherInteractor;
    @Inject
    PreferencesRepo mPrefsRepo;
    private Disposable mUpdateWeatherDisposable;

    @Override
    public void onCreate() {
        super.onCreate();
        App.getComponentManager().addWeatherComponent().inject(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.d("Weather update service is started");
        updateWeather(this);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.getComponentManager().removeWeatherComponent();
        Timber.d("Weather update service is destroyed");
        stopUpdateWeatherProcess();
    }

    private void updateWeather(Context context) {
        String city = mPrefsRepo.getCurrentCity();
        if (city == null) {
            return;
        }

        WeatherParams params = new WeatherParams(city);

        mUpdateWeatherDisposable = mWeatherInteractor.getCurrentWeather(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        weatherData -> {
                            Timber.d("updateCurrentWeather->onNext()");
                            mPrefsRepo.setLastWeatherData(weatherData);
                            long updateTimestamp = System.currentTimeMillis();
                            mPrefsRepo.setLastUpdateTimestamp(updateTimestamp);

                            Intent intent = new Intent(ACTION_WEATHER_UPDATE);
                            intent.putExtra(KEY_WEATHER_DATA, weatherData);
                            intent.putExtra(KEY_WEATHER_UPDATE_TIMESTAMP, updateTimestamp);
                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                        },
                        error -> {
                            Timber.d("updateCurrentWeather->onError(): %s", error.toString());
                            mUpdateWeatherDisposable = null;
                            stopSelf();
                        },
                        () -> {
                            Timber.d("updateCurrentWeather->onComplete()");
                            mUpdateWeatherDisposable = null;
                            stopSelf();
                        },
                        disposable -> {
                            Timber.d("updateCurrentWeather->onSubscribe()");
                        }
                );
    }

    private void stopUpdateWeatherProcess() {
        if (mUpdateWeatherDisposable != null && !mUpdateWeatherDisposable.isDisposed()) {
            mUpdateWeatherDisposable.dispose();
        }
        mUpdateWeatherDisposable = null;
    }
}
