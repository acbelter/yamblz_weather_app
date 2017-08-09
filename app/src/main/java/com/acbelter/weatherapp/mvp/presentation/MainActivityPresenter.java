package com.acbelter.weatherapp.mvp.presentation;

import android.support.annotation.NonNull;

import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.interactor.SettingsInteractor;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.mvp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.mvp.view.activity.MainActivityView;

import javax.inject.Inject;

public class MainActivityPresenter extends BasePresenter<MainActivityView> {

    @NonNull
    private CityInteractor cityInteractor;
    @NonNull
    private SettingsInteractor settingsInteractor;

    @Inject
    public MainActivityPresenter(@NonNull CityInteractor cityInteractor
            , @NonNull SettingsInteractor settingsInteractor) {
        this.cityInteractor = cityInteractor;
        this.settingsInteractor = settingsInteractor;
    }

    public void showCityList() {
        if (getView() != null)
            unSubscribeOnDetach(cityInteractor.getFavorites()
                    .subscribe(cityList -> {
                        CityData selectedCity = settingsInteractor.getUserSettings().getSelectedCity();
                        int index = cityList.indexOf(selectedCity);
                        cityList.remove(index);
                        cityList.add(0, selectedCity);
                        getView().showCityList(cityList);
                    }));
    }

    public void showSelectedWeather(@NonNull CityData cityData) {
        cityInteractor.saveSelectedCity(cityData);
    }
}
