package com.acbelter.weatherapp.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context mAppContext;

    public AppModule(Context context) {
        mAppContext = context.getApplicationContext();
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mAppContext;
    }
}
