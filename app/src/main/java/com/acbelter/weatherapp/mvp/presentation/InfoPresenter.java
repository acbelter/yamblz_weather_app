package com.acbelter.weatherapp.mvp.presentation;

import com.acbelter.weatherapp.mvp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.mvp.view.about.InfoView;
import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

@InjectViewState
public class InfoPresenter extends BasePresenter<InfoView> {

    @Inject
    public InfoPresenter() {

    }

    public void showAppVersion() {
        getViewState().showAppVersion();
    }
}
