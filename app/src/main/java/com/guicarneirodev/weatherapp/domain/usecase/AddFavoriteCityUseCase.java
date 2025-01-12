package com.guicarneirodev.weatherapp.domain.usecase;

import com.guicarneirodev.weatherapp.data.remote.dto.FavoriteCityDTO;
import com.guicarneirodev.weatherapp.domain.repository.FavoriteCityRepository;
import javax.inject.Inject;

public class AddFavoriteCityUseCase {
    private final FavoriteCityRepository favoriteCityRepository;

    @Inject
    public AddFavoriteCityUseCase(FavoriteCityRepository favoriteCityRepository) {
        this.favoriteCityRepository = favoriteCityRepository;
    }

    public FavoriteCityDTO execute(FavoriteCityDTO city) {
        return favoriteCityRepository.addFavoriteCity(city);
    }
}
