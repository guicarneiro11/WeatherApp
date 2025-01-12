package com.guicarneirodev.weatherapp.di;

import com.guicarneirodev.weatherapp.data.repository.WeatherRepositoryImpl;
import com.guicarneirodev.weatherapp.data.repository.FavoriteCityRepositoryImpl;
import com.guicarneirodev.weatherapp.domain.repository.WeatherRepository;
import com.guicarneirodev.weatherapp.domain.repository.FavoriteCityRepository;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract WeatherRepository bindWeatherRepository(WeatherRepositoryImpl impl);

    @Binds
    @Singleton
    abstract FavoriteCityRepository bindFavoriteCityRepository(FavoriteCityRepositoryImpl impl);
}
