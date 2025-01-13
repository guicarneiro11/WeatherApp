package com.guicarneirodev.weatherapp.domain.repository;

import com.guicarneirodev.weatherapp.data.remote.dto.FavoriteCityDTO;
import java.util.List;

public interface FavoriteCityRepository {
    interface Callback<T> {
        void onSuccess(T result);
        void onError(String error);
    }

    void getFavoriteCities(String userId, Callback<List<FavoriteCityDTO>> callback);
    void addFavoriteCity(FavoriteCityDTO city, Callback<FavoriteCityDTO> callback);
    void deleteFavoriteCity(long id, Callback<Void> callback);
}
