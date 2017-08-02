package com.acbelter.weatherapp.mvp.view.settings;

import com.acbelter.weatherapp.domain.model.settings.SettingsData;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface SettingsView extends MvpView {

    void setSettings(SettingsData settings);
}
