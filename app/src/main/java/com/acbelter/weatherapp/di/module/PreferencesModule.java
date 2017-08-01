package com.acbelter.weatherapp.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.acbelter.weatherapp.data.repository.preference.PreferencesRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferencesModule {
    @Provides
    @Singleton
    PreferencesRepo providePreferences(SharedPreferences sharedPreferences) {
        return new PreferencesRepo(sharedPreferences);
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences("storage", Context.MODE_PRIVATE);
    }
}