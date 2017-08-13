package com.acbelter.weatherapp.mvp.presentation;

import com.acbelter.weatherapp.mvp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.mvp.view.about.InfoView;

import javax.inject.Inject;

public class InfoPresenter extends BasePresenter<InfoView> {

    @Inject
    public InfoPresenter() {

    }

    public void showAppVersion() {
        if (getView() != null)
            getView().showAppVersion();
    }
}
