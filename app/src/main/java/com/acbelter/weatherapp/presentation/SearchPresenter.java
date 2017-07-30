package com.acbelter.weatherapp.presentation;

import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.domain.model.weather.WeatherParams;
import com.acbelter.weatherapp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.ui.search.SearchView;
import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import timber.log.Timber;

@InjectViewState
public class SearchPresenter extends BasePresenter<SearchView> {

    private CityInteractor mCityInteractor;
    private WeatherInteractor mWeatherInteractor;

    @Inject
    public SearchPresenter(CityInteractor cityInteractor, WeatherInteractor weatherInteractor) {
        mCityInteractor = cityInteractor;
        mWeatherInteractor = weatherInteractor;
    }

    public void showCityList(String input) {
        CityParams cityParams = new CityParams(input);
        unsubscribeOnDetach(mCityInteractor.getCityList(cityParams)
                .subscribe(cityDatas ->
                                getViewState().updateCityList(cityDatas),
                        throwable -> getViewState().showError()));

    }

    public void saveSelectedCityAndWeather(CityData cityData) {
        mCityInteractor.saveSelectedCity(cityData);
        WeatherParams params = new WeatherParams(cityData.getFormattedAddress());
        updateWeather(params);
    }

    private void updateWeather(WeatherParams params) {
        unsubscribeOnDetach(mWeatherInteractor.getCurrentWeather(params)
                .subscribe(weatherData -> {
                            Timber.d("getCurrentWeather->onNext()");
                            saveWeather(weatherData);
                            closeActivity();
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
                        }
                ));
    }

    private void saveWeather(WeatherData weatherData) {
        mWeatherInteractor.saveWeather(weatherData);
    }

    public void closeActivity() {
        getViewState().close();
    }
}