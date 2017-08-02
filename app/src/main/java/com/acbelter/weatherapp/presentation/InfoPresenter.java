package com.acbelter.weatherapp.presentation;

import com.acbelter.weatherapp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.ui.about.InfoView;
import com.arellomobile.mvp.InjectViewState;

@InjectViewState
public class InfoPresenter extends BasePresenter<InfoView> {
    public InfoPresenter() {

    }

    public void showAppVersion() {
        getViewState().showAppVersion();
    }
}
