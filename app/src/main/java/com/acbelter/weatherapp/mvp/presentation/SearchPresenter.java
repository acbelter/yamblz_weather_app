package com.acbelter.weatherapp.mvp.presentation;

import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.domain.model.weather.CurrentWeatherFavorites;
import com.acbelter.weatherapp.mvp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.mvp.view.search.SearchView;

import javax.inject.Inject;

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
                .subscribe(autocompleteDatas ->
                                getView().updateCityList(autocompleteDatas),
                        throwable -> getView().showError()));

    }

    public void saveSelectedCityAndWeather(AutocompleteData autocompleteData) {
        cityInteractor.getCityData(autocompleteData)
                .subscribe(cityData -> cityInteractor.saveSelectedCity(cityData));
        updateWeather();
    }

    private void updateWeather() {
        if (getView() == null)
            return;
        unSubscribeOnDetach(weatherInteractor.updateWeather()
                .subscribe(fullWeatherModel -> closeActivity()));
    }

    private void saveWeather(CurrentWeatherFavorites currentWeatherFavorites) {
//        weatherInteractor.saveWeather(currentWeatherFavorites);
    }

    public void closeActivity() {
        if (getView() != null)
            getView().close();
    }
}