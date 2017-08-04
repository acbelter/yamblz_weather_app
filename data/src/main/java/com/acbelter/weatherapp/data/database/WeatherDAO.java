package com.acbelter.weatherapp.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.acbelter.weatherapp.data.dbmodel.DatabaseWeatherData;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface WeatherDAO {
    @Query("SELECT * FROM DatabaseWeatherData")
    Flowable<List<DatabaseWeatherData>> getAllWeatherRecords();

    @Query("SELECT * FROM DatabaseWeatherData WHERE city_name = :cityName ")
    Maybe<DatabaseWeatherData> getWeatherByCityName(String cityName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeather(DatabaseWeatherData weather);

    @Query("DELETE FROM DatabaseWeatherData WHERE city_name = :cityName")
    void deleteAllWeatherRecords(String cityName);

    @Query("DELETE FROM DatabaseWeatherData")
    void deleteAllWeatherRecords();
}
