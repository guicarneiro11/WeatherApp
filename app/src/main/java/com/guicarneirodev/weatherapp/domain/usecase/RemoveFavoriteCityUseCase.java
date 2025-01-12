package com.guicarneirodev.weatherapp.domain.usecase;

import com.guicarneirodev.weatherapp.domain.repository.FavoriteCityRepository;
import javax.inject.Inject;

public class RemoveFavoriteCityUseCase {
    private final FavoriteCityRepository favoriteCityRepository;

    @Inject
    public RemoveFavoriteCityUseCase(FavoriteCityRepository favoriteCityRepository) {
        this.favoriteCityRepository = favoriteCityRepository;
    }

    public void execute(long cityId) {
        favoriteCityRepository.deleteFavoriteCity(cityId);
    }
}
