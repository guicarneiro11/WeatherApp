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

    public void execute(FavoriteCityDTO city, FavoriteCityRepository.Callback<FavoriteCityDTO> callback) {
        favoriteCityRepository.addFavoriteCity(city, callback);
    }
}
