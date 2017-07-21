package com.acbelter.weatherapp.di.module;

import android.content.Context;

import com.acbelter.weatherapp.data.repository.PreferencesRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferencesModule {
    @Provides
    @Singleton
    PreferencesRepo providePreferences(Context context) {
        return new PreferencesRepo(context);
    }
}