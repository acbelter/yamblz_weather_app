package com.acbelter.weatherapp.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.acbelter.weatherapp.data.dbmodel.DatabaseWeatherData;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface WeatherDAO {
    @Query("SELECT * FROM DatabaseWeatherData")
    Single<List<DatabaseWeatherData>> getAllWeatherRecords();

    @Query("SELECT * FROM DatabaseWeatherData WHERE coordinates = :coordinates ")
    Single<DatabaseWeatherData> getWeatherByCityName(String coordinates);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeather(DatabaseWeatherData weather);

    @Update
    void updateWeather(DatabaseWeatherData weather);

    @Query("DELETE FROM DatabaseWeatherData")
    void deleteAllWeatherRecords();
}
