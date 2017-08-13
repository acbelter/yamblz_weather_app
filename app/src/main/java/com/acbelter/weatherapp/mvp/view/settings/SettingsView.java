package com.acbelter.weatherapp.mvp.view.settings;

import com.acbelter.weatherapp.domain.model.settings.SettingsData;
import com.acbelter.weatherapp.mvp.view.common.BaseView;

public interface SettingsView extends BaseView {

    void setSettings(SettingsData settings);
}
