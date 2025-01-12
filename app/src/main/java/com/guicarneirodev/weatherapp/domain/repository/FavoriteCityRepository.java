package com.guicarneirodev.weatherapp.domain.repository;

import com.guicarneirodev.weatherapp.data.remote.dto.FavoriteCityDTO;
import java.util.List;

public interface FavoriteCityRepository {
    List<FavoriteCityDTO> getFavoriteCities(String userId);
    FavoriteCityDTO addFavoriteCity(FavoriteCityDTO city);
    void deleteFavoriteCity(long id);
    void cacheFavoriteCities(List<FavoriteCityDTO> cities);
    List<FavoriteCityDTO> getCachedFavoriteCities(String userId);
}
