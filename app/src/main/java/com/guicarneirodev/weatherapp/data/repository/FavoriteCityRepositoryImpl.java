package com.guicarneirodev.weatherapp.data.repository;

import com.guicarneirodev.weatherapp.data.local.dao.FavoriteCityDao;
import com.guicarneirodev.weatherapp.data.local.entity.FavoriteCityEntity;
import com.guicarneirodev.weatherapp.data.mapper.FavoriteCityMapper;
import com.guicarneirodev.weatherapp.data.remote.api.WeatherApi;
import com.guicarneirodev.weatherapp.data.remote.dto.FavoriteCityDTO;
import com.guicarneirodev.weatherapp.domain.repository.FavoriteCityRepository;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Response;
import java.io.IOException;

public class FavoriteCityRepositoryImpl implements FavoriteCityRepository {
    private final WeatherApi weatherApi;
    private final FavoriteCityDao favoriteCityDao;

    @Inject
    public FavoriteCityRepositoryImpl(WeatherApi weatherApi, FavoriteCityDao favoriteCityDao) {
        this.weatherApi = weatherApi;
        this.favoriteCityDao = favoriteCityDao;
    }

    @Override
    public List<FavoriteCityDTO> getFavoriteCities(String userId) {
        try {
            Response<List<FavoriteCityDTO>> response = weatherApi.getFavoriteCities(userId).execute();
            if (response.isSuccessful() && response.body() != null) {
                List<FavoriteCityDTO> cities = response.body();
                cacheFavoriteCities(cities);
                return cities;
            } else {
                return getCachedFavoriteCities(userId);
            }
        } catch (IOException e) {
            return getCachedFavoriteCities(userId);
        }
    }

    @Override
    public FavoriteCityDTO addFavoriteCity(FavoriteCityDTO city) {
        try {
            Response<FavoriteCityDTO> response = weatherApi.addFavoriteCity(city).execute();
            if (response.isSuccessful() && response.body() != null) {
                return response.body();
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void deleteFavoriteCity(long id) {
        try {
            weatherApi.deleteFavoriteCity(id).execute();
            favoriteCityDao.deleteById(id);
        } catch (IOException e) {
            // Tratar erro
        }
    }

    @Override
    public void cacheFavoriteCities(List<FavoriteCityDTO> cities) {
        if (cities != null) {
            for (FavoriteCityDTO city : cities) {
                FavoriteCityEntity entity = FavoriteCityMapper.toEntity(city);
                favoriteCityDao.insert(entity);
            }
        }
    }

    @Override
    public List<FavoriteCityDTO> getCachedFavoriteCities(String userId) {
        List<FavoriteCityEntity> entities = favoriteCityDao.getFavoriteCities(userId);
        List<FavoriteCityDTO> dtos = new ArrayList<>();

        for (FavoriteCityEntity entity : entities) {
            dtos.add(FavoriteCityMapper.toDto(entity));
        }

        return dtos;
    }
}
