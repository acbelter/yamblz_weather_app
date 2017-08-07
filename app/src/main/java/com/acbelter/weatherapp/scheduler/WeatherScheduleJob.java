package com.acbelter.weatherapp.scheduler;

import android.support.annotation.NonNull;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.BuildConfig;
import com.acbelter.weatherapp.domain.interactor.SettingsInteractor;
import com.acbelter.weatherapp.domain.interactor.WeatherInteractor;
import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

@Singleton
public class WeatherScheduleJob extends Job {

    static final String TAG = "show_notification_job_tag";
    private static final double FLEX_TIME_PERCENT = 0.5; //flex time is 50% of full period interval

    @Inject
    WeatherInteractor weatherInteractor;

    @Inject
    SettingsInteractor settingsInteractor;

    public WeatherScheduleJob() {
        App.getInstance().plusActivityComponent().inject(this);
    }

    @NonNull
    @Override
    protected Result onRunJob(Params params) {
        Timber.v("onRunJob");
        serializeCurrentWeather();

        return Result.SUCCESS;
    }

    private void serializeCurrentWeather() {
        settingsInteractor.getUserSettings()
                .subscribe(settingsModel
                        -> weatherInteractor.updateWeather()
                        .subscribe(fullWeatherModel -> {
                        }, e -> {
                        }));
    }

    public void startJob() {
        Timber.v("startJob");
        if (BuildConfig.DEBUG)
            settingsInteractor.getUserSettings().subscribe(settings ->
                    new JobRequest.Builder(TAG)
                            .setPeriodic(TimeUnit.MILLISECONDS.toMillis(61000)
                                    , TimeUnit.MILLISECONDS.toMillis(35000))
                            .setUpdateCurrent(true)
                            .setPersisted(true)
                            .build()
                            .schedule());
        else
            settingsInteractor.getUserSettings().subscribe(settings ->
                    new JobRequest.Builder(TAG)
                            .setPeriodic(TimeUnit.MILLISECONDS.toMillis(settings.getUpdateWeatherInterval())
                                    , TimeUnit.MILLISECONDS.toMillis((long) ((double) settings.getUpdateWeatherInterval() * FLEX_TIME_PERCENT)))
                            .setUpdateCurrent(true)
                            .setPersisted(true)
                            .build()
                            .schedule());

    }
}
