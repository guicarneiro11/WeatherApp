package com.guicarneirodev.weatherapp.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.guicarneirodev.weatherapp.data.local.entity.WeatherInfoEntity;

import java.util.List;

@Dao
public interface WeatherInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(WeatherInfoEntity weatherInfo);

    @Query("SELECT * FROM weather_info WHERE cityName = :cityName ORDER BY id DESC LIMIT 1")
    WeatherInfoEntity getLatestWeatherInfo(String cityName);

    @Query("SELECT * FROM weather_info WHERE cityName = :cityName ORDER BY id DESC")
    List<WeatherInfoEntity> getWeatherHistory(String cityName);

    @Query("DELETE FROM weather_info WHERE cityName = :cityName")
    void deleteWeatherInfoForCity(String cityName);
}
