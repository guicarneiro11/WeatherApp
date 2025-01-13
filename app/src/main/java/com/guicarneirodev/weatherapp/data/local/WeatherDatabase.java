package com.guicarneirodev.weatherapp.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.guicarneirodev.weatherapp.data.local.dao.FavoriteCityDao;
import com.guicarneirodev.weatherapp.data.local.dao.WeatherInfoDao;
import com.guicarneirodev.weatherapp.data.local.entity.FavoriteCityEntity;
import com.guicarneirodev.weatherapp.data.local.entity.WeatherInfoEntity;

@Database(
        entities = {WeatherInfoEntity.class, FavoriteCityEntity.class},
        version = 1,
        exportSchema = false
)
public abstract class WeatherDatabase extends RoomDatabase {
    public abstract WeatherInfoDao weatherInfoDao();
    public abstract FavoriteCityDao favoriteCityDao();
}