package com.acbelter.weatherapp.di.module;

import android.content.Context;
import android.os.Build;

import com.acbelter.weatherapp.BuildConfig;
import com.acbelter.weatherapp.scheduler.ScheduleJobCreator;
import com.evernote.android.job.JobManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

@Module
public class AppModule {

    private final Context appContext;

    public AppModule(Context context) {
        appContext = context.getApplicationContext();

        initScheduleJob();
    }

    private void initScheduleJob() {
        JobManager.create(appContext).addJobCreator(new ScheduleJobCreator());
        Timber.d("Config = " + BuildConfig.BUILD_TYPE);
        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT <= Build.VERSION_CODES.M)
            JobManager.instance().getConfig().setAllowSmallerIntervalsForMarshmallow(true);
    }

    @Provides
    @Singleton
    Context provideContext() {
        return appContext;
    }
}
