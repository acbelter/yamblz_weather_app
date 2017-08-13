package com.acbelter.weatherapp.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.acbelter.weatherapp.data.dbmodel.DatabaseWeatherData;

@Database(entities = {DatabaseWeatherData.class}, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {
    public abstract WeatherDAO weatherDAO();
}
