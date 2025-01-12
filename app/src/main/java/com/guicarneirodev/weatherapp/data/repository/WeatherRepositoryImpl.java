package com.guicarneirodev.weatherapp.data.repository;

import com.guicarneirodev.weatherapp.data.local.dao.WeatherInfoDao;
import com.guicarneirodev.weatherapp.data.local.entity.WeatherInfoEntity;
import com.guicarneirodev.weatherapp.data.mapper.WeatherMapper;
import com.guicarneirodev.weatherapp.data.remote.api.WeatherApi;
import com.guicarneirodev.weatherapp.data.remote.dto.WeatherDataDTO;
import com.guicarneirodev.weatherapp.domain.repository.WeatherRepository;
import javax.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;
import retrofit2.Response;
import java.io.IOException;

public class WeatherRepositoryImpl implements WeatherRepository {
    private final WeatherApi weatherApi;
    private final WeatherInfoDao weatherInfoDao;

    @Inject
    public WeatherRepositoryImpl(WeatherApi weatherApi, WeatherInfoDao weatherInfoDao) {
        this.weatherApi = weatherApi;
        this.weatherInfoDao = weatherInfoDao;
    }

    @Override
    public WeatherDataDTO getWeatherData(String cityName, String userId) {
        try {
            Response<WeatherDataDTO> response = weatherApi.getWeatherData(cityName, userId).execute();
            if (response.isSuccessful() && response.body() != null) {
                WeatherDataDTO weatherData = response.body();
                cacheWeatherData(weatherData);
                return weatherData;
            } else {
                return getCachedWeatherData(cityName);
            }
        } catch (IOException e) {
            return getCachedWeatherData(cityName);
        }
    }

    @Override
    public List<WeatherDataDTO> getWeatherHistory(String cityName, String userId) {
        try {
            Response<WeatherDataDTO.ListResponseDTO> response =
                    weatherApi.getWeatherHistory(cityName, userId).execute();
            if (response.isSuccessful() && response.body() != null) {
                return response.body().getData();
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void cacheWeatherData(WeatherDataDTO weatherData) {
        if (weatherData != null && weatherData.getInfo() != null) {
            WeatherInfoEntity entity = WeatherMapper.toEntity(weatherData.getInfo());
            weatherInfoDao.insert(entity);
        }
    }

    @Override
    public WeatherDataDTO getCachedWeatherData(String cityName) {
        WeatherInfoEntity entity = weatherInfoDao.getLatestWeatherInfo(cityName);
        if (entity != null) {
            WeatherDataDTO dto = new WeatherDataDTO();
            dto.setInfo(WeatherMapper.toDto(entity));
            dto.setCreatedAt(LocalDateTime.now()); //
            return dto;
        }
        return null;
    }
}
