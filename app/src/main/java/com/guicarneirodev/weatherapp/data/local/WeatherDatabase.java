package com.guicarneirodev.weatherapp.data.local;

import androidx.databinding.adapters.Converters;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.guicarneirodev.weatherapp.data.local.entity.WeatherInfoEntity;
import com.guicarneirodev.weatherapp.data.local.entity.FavoriteCityEntity;
import com.guicarneirodev.weatherapp.data.local.dao.WeatherInfoDao;
import com.guicarneirodev.weatherapp.data.local.dao.FavoriteCityDao;

@Database(
        entities = {WeatherInfoEntity.class, FavoriteCityEntity.class},
        version = 1
)
@TypeConverters({Converters.class})
public abstract class WeatherDatabase extends RoomDatabase {
    public abstract WeatherInfoDao weatherInfoDao();
    public abstract FavoriteCityDao favoriteCityDao();
}