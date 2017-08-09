package com.acbelter.weatherapp.mvp.presentation;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.domain.interactor.SettingsInteractor;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;
import com.acbelter.weatherapp.mvp.presentation.common.BasePresenter;
import com.acbelter.weatherapp.mvp.view.settings.SettingsView;
import com.acbelter.weatherapp.scheduler.WeatherScheduleJob;

import javax.inject.Inject;

public class SettingsPresenter extends BasePresenter<SettingsView> {

    private final SettingsInteractor settingsInteractor;

    @Inject
    WeatherScheduleJob scheduleJob;

    @Inject
    public SettingsPresenter(SettingsInteractor settingsInteractor) {
        this.settingsInteractor = settingsInteractor;

        App.getInstance().getAppComponent().inject(this);
    }

    public void showSettings() {
        if (getView() == null)
            return;
        unSubscribeOnDetach(settingsInteractor.getUserSettings()
                .subscribe(settings -> getView().setSettings(settings)));
    }

    public void saveTemperatureMetric(TemperatureMetric metric) {
        settingsInteractor.saveTemperatureMetric(metric);
    }

    public void saveUpdateInterval(long interval) {
        settingsInteractor.saveUpdateInterval(interval);

        scheduleJob.startJob();
    }
}
