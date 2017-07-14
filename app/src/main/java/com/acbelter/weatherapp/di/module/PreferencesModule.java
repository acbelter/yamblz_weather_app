package com.acbelter.weatherapp.di.module;

import android.content.Context;

import com.acbelter.weatherapp.PreferencesStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferencesModule {
    @Provides
    @Singleton
    PreferencesStorage providePreferences(Context context) {
        return new PreferencesStorage(context);
    }
}