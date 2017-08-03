package com.acbelter.weatherapp.mvp.presentation;

import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.mvp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.mvp.view.search.SearchView;

import javax.inject.Inject;

import timber.log.Timber;

public class SearchPresenter extends BasePresenter<SearchView> {

    private CityInteractor cityInteractor;
    private WeatherInteractor weatherInteractor;

    @Inject
    public SearchPresenter(CityInteractor cityInteractor, WeatherInteractor weatherInteractor) {
        this.cityInteractor = cityInteractor;
        this.weatherInteractor = weatherInteractor;
    }

    public void showCityList(String input) {
        if (getView() == null)
            return;
        CityParams cityParams = new CityParams(input);
        unSubscribeOnDetach(cityInteractor.getCityList(cityParams)
                .subscribe(cityDatas ->
                                getView().updateCityList(cityDatas),
                        throwable -> getView().showError()));

    }

    public void saveSelectedCityAndWeather(CityData cityData) {
        cityInteractor.saveSelectedCity(cityData);
        updateWeather();
    }

    private void updateWeather() {
        if (getView() == null)
            return;
        unSubscribeOnDetach(weatherInteractor.updateWeather()
                .subscribe(weatherData -> {
                            Timber.d("getCurrentWeather->onNext()");
//                            saveWeather(weatherData);
                            closeActivity();
                        },
                        error -> {
                            Timber.d("getCurrentWeather->onError(): %s", error.toString());
                            getView().showError();
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
        weatherInteractor.saveWeather(weatherData);
    }

    public void closeActivity() {
        if (getView() != null)
            getView().close();
    }
}