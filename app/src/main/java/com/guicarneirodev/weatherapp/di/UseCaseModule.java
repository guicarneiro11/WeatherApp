package com.guicarneirodev.weatherapp.di;

import com.guicarneirodev.weatherapp.domain.repository.FavoriteCityRepository;
import com.guicarneirodev.weatherapp.domain.repository.WeatherRepository;
import com.guicarneirodev.weatherapp.domain.usecase.*;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UseCaseModule {

    @Provides
    GetWeatherUseCase provideGetWeatherUseCase(WeatherRepository repository) {
        return new GetWeatherUseCase(repository);
    }

    @Provides
    GetWeatherHistoryUseCase provideGetWeatherHistoryUseCase(WeatherRepository repository) {
        return new GetWeatherHistoryUseCase(repository);
    }

    @Provides
    GetFavoriteCitiesUseCase provideGetFavoriteCitiesUseCase(FavoriteCityRepository repository) {
        return new GetFavoriteCitiesUseCase(repository);
    }

    @Provides
    AddFavoriteCityUseCase provideAddFavoriteCityUseCase(FavoriteCityRepository repository) {
        return new AddFavoriteCityUseCase(repository);
    }

    @Provides
    RemoveFavoriteCityUseCase provideRemoveFavoriteCityUseCase(FavoriteCityRepository repository) {
        return new RemoveFavoriteCityUseCase(repository);
    }
}
