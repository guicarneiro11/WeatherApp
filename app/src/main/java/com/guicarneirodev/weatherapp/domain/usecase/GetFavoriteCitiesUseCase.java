package com.guicarneirodev.weatherapp.domain.usecase;

import com.guicarneirodev.weatherapp.data.remote.dto.FavoriteCityDTO;
import com.guicarneirodev.weatherapp.domain.repository.FavoriteCityRepository;
import javax.inject.Inject;
import java.util.List;

public class GetFavoriteCitiesUseCase {
    private final FavoriteCityRepository favoriteCityRepository;

    @Inject
    public GetFavoriteCitiesUseCase(FavoriteCityRepository favoriteCityRepository) {
        this.favoriteCityRepository = favoriteCityRepository;
    }

    public void execute(String userId, FavoriteCityRepository.Callback<List<FavoriteCityDTO>> callback) {
        favoriteCityRepository.getFavoriteCities(userId, callback);
    }
}
