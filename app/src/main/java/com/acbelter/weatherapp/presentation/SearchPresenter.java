package com.acbelter.weatherapp.presentation;

import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.model.city.CityData;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.ui.search.SearchView;
import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class SearchPresenter extends BasePresenter<SearchView> {

    private CityInteractor mCityInteractor;

    @Inject
    public SearchPresenter(CityInteractor cityInteractor) {
        mCityInteractor = cityInteractor;
    }

    public void showCityList(String input) {

        CityParams cityParams = new CityParams(input);
        unsubscribeOnDetach(mCityInteractor.getCityList(cityParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cityDatas ->
                                getViewState().updateCityList(cityDatas),
                        throwable -> getViewState().showError()));

    }

    public void saveSelectedCity(CityData cityData) {
        mCityInteractor.saveSelectedCity(cityData);
    }

    public void closeActivity() {
        getViewState().close();
    }
}