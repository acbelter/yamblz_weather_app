package com.acbelter.weatherapp.di.module;

import android.content.Context;

import com.acbelter.weatherapp.data.database.DatabaseService;
import com.acbelter.weatherapp.data.database.DatabaseServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    @Provides
    @Singleton
    DatabaseService provideDatabaseService(Context context) {
        return new DatabaseServiceImpl(context);
    }
}
