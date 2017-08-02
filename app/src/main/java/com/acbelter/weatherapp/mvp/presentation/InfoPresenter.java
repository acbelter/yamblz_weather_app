package com.acbelter.weatherapp.mvp.presentation;

import com.acbelter.weatherapp.mvp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.mvp.view.about.InfoView;
import com.arellomobile.mvp.InjectViewState;

@InjectViewState
public class InfoPresenter extends BasePresenter<InfoView> {
    public InfoPresenter() {

    }

    public void showAppVersion() {
        getViewState().showAppVersion();
    }
}
