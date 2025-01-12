package com.guicarneirodev.weatherapp.data.remote.api;

import android.content.Context;
import androidx.room.Room;
import com.guicarneirodev.weatherapp.data.local.WeatherDatabase;
import com.guicarneirodev.weatherapp.data.local.dao.WeatherInfoDao;
import com.guicarneirodev.weatherapp.data.local.dao.FavoriteCityDao;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    WeatherDatabase provideDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(
                context,
                WeatherDatabase.class,
                "weather_database"
        ).build();
    }

    @Provides
    WeatherInfoDao provideWeatherInfoDao(WeatherDatabase database) {
        return database.weatherInfoDao();
    }

    @Provides
    FavoriteCityDao provideFavoriteCityDao(WeatherDatabase database) {
        return database.favoriteCityDao();
    }
}
