package com.acbelter.weatherapp.mvp.presentation;

import com.acbelter.weatherapp.mvp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.mvp.view.error.ErrorView;

import javax.inject.Inject;

public class ErrorPresenter extends BasePresenter<ErrorView> {

    @Inject
    public ErrorPresenter() {
    }

    public void showNoCityError() {
        if (getView() != null) {
            getView().showNoCityError();
        }
    }

}
