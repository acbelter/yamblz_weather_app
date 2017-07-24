package com.acbelter.weatherapp.presentation;

import com.acbelter.weatherapp.domain.interactor.CityInteractor;
import com.acbelter.weatherapp.domain.model.city.CityParams;
import com.acbelter.weatherapp.ui.search.SearchView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class SearchPresenter extends MvpPresenter<SearchView> {

    private CityInteractor mCityInteractor;
    private Disposable mCurrentWeatherDisposable;

    @Inject
    public SearchPresenter(CityInteractor cityInteractor) {
        mCityInteractor = cityInteractor;
    }

    public void showCityList(String input) {

        CityParams cityParams = new CityParams(input);
        mCurrentWeatherDisposable = mCityInteractor.getCityList(cityParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((cityDatas, throwable) -> {
                    Timber.v("PRESENTER = " + getViewState());
                    getViewState().updateCityList(cityDatas);
                });

    }

    public void closeActivity() {
        getViewState().close();
    }

    public void stopGetCurrentWeatherProcess() {
        if (mCurrentWeatherDisposable != null && !mCurrentWeatherDisposable.isDisposed()) {
            mCurrentWeatherDisposable.dispose();
        }
        mCurrentWeatherDisposable = null;
    }
}