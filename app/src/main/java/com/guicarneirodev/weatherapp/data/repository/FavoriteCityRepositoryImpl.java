package com.guicarneirodev.weatherapp.data.repository;

import android.os.Handler;
import android.os.Looper;

import com.guicarneirodev.weatherapp.data.local.dao.FavoriteCityDao;
import com.guicarneirodev.weatherapp.data.local.entity.FavoriteCityEntity;
import com.guicarneirodev.weatherapp.data.mapper.FavoriteCityMapper;
import com.guicarneirodev.weatherapp.data.remote.api.WeatherApi;
import com.guicarneirodev.weatherapp.data.remote.dto.FavoriteCityDTO;
import com.guicarneirodev.weatherapp.domain.repository.FavoriteCityRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import retrofit2.Response;

public class FavoriteCityRepositoryImpl implements FavoriteCityRepository {
    private final WeatherApi weatherApi;
    private final FavoriteCityDao favoriteCityDao;
    private final ExecutorService executorService;

    @Inject
    public FavoriteCityRepositoryImpl(WeatherApi weatherApi, FavoriteCityDao favoriteCityDao) {
        this.weatherApi = weatherApi;
        this.favoriteCityDao = favoriteCityDao;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void getFavoriteCities(String userId, Callback<List<FavoriteCityDTO>> callback) {
        executorService.execute(() -> {
            try {
                Response<List<FavoriteCityDTO>> response = weatherApi.getFavoriteCities(userId).execute();
                if (response.isSuccessful() && response.body() != null) {
                    List<FavoriteCityDTO> cities = response.body();
                    cacheFavoriteCities(cities);
                    new Handler(Looper.getMainLooper()).post(() ->
                            callback.onSuccess(cities)
                    );
                } else {
                    List<FavoriteCityDTO> cachedCities = getCachedFavoriteCities(userId);
                    new Handler(Looper.getMainLooper()).post(() ->
                            callback.onSuccess(cachedCities)
                    );
                }
            } catch (IOException e) {
                List<FavoriteCityDTO> cachedCities = getCachedFavoriteCities(userId);
                if (!cachedCities.isEmpty()) {
                    new Handler(Looper.getMainLooper()).post(() ->
                            callback.onSuccess(cachedCities)
                    );
                } else {
                    new Handler(Looper.getMainLooper()).post(() ->
                            callback.onError(e.getMessage())
                    );
                }
            }
        });
    }

    @Override
    public void addFavoriteCity(FavoriteCityDTO city, Callback<FavoriteCityDTO> callback) {
        executorService.execute(() -> {
            try {
                Response<FavoriteCityDTO> response = weatherApi.addFavoriteCity(city).execute();
                if (response.isSuccessful() && response.body() != null) {
                    FavoriteCityDTO addedCity = response.body();
                    FavoriteCityEntity entity = FavoriteCityMapper.toEntity(addedCity);
                    favoriteCityDao.insert(entity);
                    new Handler(Looper.getMainLooper()).post(() ->
                            callback.onSuccess(addedCity)
                    );
                } else {
                    new Handler(Looper.getMainLooper()).post(() ->
                            callback.onError("Failed to add city")
                    );
                }
            } catch (IOException e) {
                new Handler(Looper.getMainLooper()).post(() ->
                        callback.onError(e.getMessage())
                );
            }
        });
    }

    @Override
    public void deleteFavoriteCity(long id, Callback<Void> callback) {
        executorService.execute(() -> {
            try {
                Response<Void> response = weatherApi.deleteFavoriteCity(id).execute();
                if (response.isSuccessful()) {
                    favoriteCityDao.deleteById(id);
                    new Handler(Looper.getMainLooper()).post(() ->
                            callback.onSuccess(null)
                    );
                } else {
                    new Handler(Looper.getMainLooper()).post(() ->
                            callback.onError("Failed to delete city")
                    );
                }
            } catch (IOException e) {
                new Handler(Looper.getMainLooper()).post(() ->
                        callback.onError(e.getMessage())
                );
            }
        });

    }

    public void cacheFavoriteCities(List<FavoriteCityDTO> cities) {
        if (cities != null) {
            for (FavoriteCityDTO city : cities) {
                FavoriteCityEntity entity = FavoriteCityMapper.toEntity(city);
                favoriteCityDao.insert(entity);
            }
        }
    }

    public List<FavoriteCityDTO> getCachedFavoriteCities(String userId) {
        List<FavoriteCityEntity> entities = favoriteCityDao.getFavoriteCities(userId);
        List<FavoriteCityDTO> dtos = new ArrayList<>();

        for (FavoriteCityEntity entity : entities) {
            dtos.add(FavoriteCityMapper.toDto(entity));
        }

        return dtos;
    }
}