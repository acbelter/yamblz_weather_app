package com.acbelter.weatherapp.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

import com.acbelter.weatherapp.data.database.WeatherDAO;
import com.acbelter.weatherapp.data.database.WeatherDatabase;
import com.acbelter.weatherapp.data.network.NetworkService;
import com.acbelter.weatherapp.data.repository.city.CityRepoImpl;
import com.acbelter.weatherapp.data.repository.preference.SettingsPreference;
import com.acbelter.weatherapp.data.repository.preference.SettingsRepoImpl;
import com.acbelter.weatherapp.data.repository.weather.WeatherRepoImpl;
import com.acbelter.weatherapp.domain.repository.CityRepo;
import com.acbelter.weatherapp.domain.repository.SettingsRepo;
import com.acbelter.weatherapp.domain.repository.WeatherRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences("storage", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    SettingsPreference providePreferences(SharedPreferences sharedPreferences) {
        return new SettingsPreference(sharedPreferences);
    }

    @Provides
    @Singleton
    CityRepo provideCityRepo(NetworkService networkService, SettingsPreference settingsPreference) {
        return new CityRepoImpl(networkService, settingsPreference);
    }

    @Provides
    @Singleton
    WeatherRepo provideWeatherRepo(NetworkService networkService, SettingsPreference settingsPreference) {
        return new WeatherRepoImpl(networkService, settingsPreference);
    }

    @Provides
    @Singleton
    SettingsRepo provideSettingsRepo(SettingsPreference settingsPreference) {
        return new SettingsRepoImpl(settingsPreference);
    }

    @Provides
    @Singleton
    public WeatherDAO getWeatherDAO(WeatherDatabase weatherDatabase) {
        return weatherDatabase.weatherDAO();
    }

    @Singleton
    @Provides
    public WeatherDatabase getWeatherDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                WeatherDatabase.class, "weather.db")
                .build();
    }
}
