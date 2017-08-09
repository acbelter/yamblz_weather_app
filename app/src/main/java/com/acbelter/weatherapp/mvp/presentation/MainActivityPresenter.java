package com.acbelter.weatherapp.mvp.presentation;

import android.support.annotation.NonNull;

import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.interactor.SettingsInteractor;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.mvp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.mvp.view.activity.MainActivityView;

import javax.inject.Inject;

public class MainActivityPresenter extends BasePresenter<MainActivityView> {

    @NonNull
    private CityInteractor cityInteractor;
    @NonNull
    private WeatherInteractor weatherInteractor;
    @NonNull
    private SettingsInteractor settingsInteractor;

    @Inject
    public MainActivityPresenter(@NonNull CityInteractor cityInteractor
            , @NonNull WeatherInteractor weatherInteractor
            , @NonNull SettingsInteractor settingsInteractor) {
        this.cityInteractor = cityInteractor;
        this.weatherInteractor = weatherInteractor;
        this.settingsInteractor = settingsInteractor;
    }

    public void showCityList() {
        if (getView() != null)
            unSubscribeOnDetach(cityInteractor.getFavorites()
                    .subscribe(cityList -> {
                        CityData selectedCity = settingsInteractor.getUserSettings().getSelectedCity();
                        int index = cityList.indexOf(selectedCity);
                        if (index != -1) {
                            cityList.remove(index);
                            cityList.add(0, selectedCity);
                        }
                        getView().showCityList(cityList);
                    }));
    }

    public void showSelectedWeather(@NonNull CityData cityData) {
        cityInteractor.saveSelectedCity(cityData);
    }

    public void deleteItem(@NonNull CityData removedItem) {
        weatherInteractor.deleteWeather(removedItem);
        unSubscribeOnDetach(cityInteractor.getFavorites()
                .subscribe(cityList -> {
                    CityData selectedCity = settingsInteractor.getUserSettings().getSelectedCity();
                    if (removedItem.equals(selectedCity) && (cityList.size() > 1)) {
                        cityInteractor.saveSelectedCity(cityList.get(1));
                    } else if (removedItem.equals(selectedCity) && (cityList.size() == 1)) {
                        cityInteractor.saveSelectedCity(cityList.get(0));
                    }
                }));
    }
}
