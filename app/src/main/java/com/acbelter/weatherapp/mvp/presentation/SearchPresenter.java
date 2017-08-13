package com.acbelter.weatherapp.mvp.presentation;

import android.support.annotation.NonNull;

import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.city.AutocompleteData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.mvp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.mvp.view.search.SearchView;

import javax.inject.Inject;

public class SearchPresenter extends BasePresenter<SearchView> {

    @NonNull
    private final CityInteractor cityInteractor;
    @NonNull
    private final WeatherInteractor weatherInteractor;

    @Inject
    public SearchPresenter(@NonNull CityInteractor cityInteractor, @NonNull WeatherInteractor weatherInteractor) {
        this.cityInteractor = cityInteractor;
        this.weatherInteractor = weatherInteractor;
    }

    public void showCityList(@NonNull String input) {
        if (getView() == null)
            return;
        CityParams cityParams = new CityParams(input);
        unSubscribeOnDetach(cityInteractor.getCityList(cityParams)
                .subscribe(autocompleteDatas ->
                                getView().updateCityList(autocompleteDatas),
                        throwable -> {
                            getView().showError();
                        }));

    }

    public void saveSelectedCityAndWeather(@NonNull AutocompleteData autocompleteData) {
        unSubscribeOnDetach(cityInteractor.getCityData(autocompleteData)
                .subscribe(cityData -> {
                    cityInteractor.saveSelectedCity(cityData);
                    weatherInteractor.getNewWeatherAndSaveToDB()
                            .subscribe();
                    closeActivity();
                }));
    }

    private void closeActivity() {
        if (getView() != null)
            getView().close();
    }
}