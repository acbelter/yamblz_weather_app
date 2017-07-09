package com.acbelter.weatherapp.presentation;

import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
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
    private WeatherInteractor mWeatherInteractor;
    private Disposable mGetWeatherDisposable;

    @Inject
    public WeatherPresenter(WeatherInteractor weatherInteractor) {
        mWeatherInteractor = weatherInteractor;
    }

    public void getWeather() {
        if (mGetWeatherDisposable != null) {
            return;
        }

        mGetWeatherDisposable = mWeatherInteractor.getWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        weatherData -> {
                            Timber.d("getWeather()->onNext()");
                            getViewState().showWeather(weatherData);
                        },
                        e -> {
                            Timber.d("getWeather()->onError(): " + e.toString());
                            getViewState().showError();
                            stopGetWeatherProcess();
                        },
                        () -> {
                            Timber.d("getWeather()->onComplete()");
                            stopGetWeatherProcess();
                        },
                        d -> {
                            Timber.d("getWeather()->onSubscribe()");
                            getViewState().showWeatherLoading();
                        }
                );
    }

    private void stopGetWeatherProcess() {
        if (mGetWeatherDisposable != null && !mGetWeatherDisposable.isDisposed()) {
            mGetWeatherDisposable.dispose();
        }
        mGetWeatherDisposable = null;
    }

    public void stop() {
        stopGetWeatherProcess();
    }
}
