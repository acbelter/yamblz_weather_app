package com.acbelter.weatherapp.mvp.presentation;

import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.mvp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.mvp.view.activity.MainActivityView;

import javax.inject.Inject;

public class MainActivityPresenter extends BasePresenter<MainActivityView> {

    private CityInteractor cityInteractor;

    @Inject
    public MainActivityPresenter(CityInteractor cityInteractor) {
        this.cityInteractor = cityInteractor;
    }

    public void showCityList() {
        if (getView() != null)
            unSubscribeOnDetach(cityInteractor.getFavorites()
                    .subscribe(cityDatas -> getView().showCityList(cityDatas)));
    }

    public void showSelectedWeather(CityData cityData) {
        cityInteractor.saveSelectedCity(cityData);
    }
}
