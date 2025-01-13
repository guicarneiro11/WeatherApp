package com.guicarneirodev.weatherapp.data.repository;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.guicarneirodev.weatherapp.data.local.dao.WeatherInfoDao;
import com.guicarneirodev.weatherapp.data.local.entity.WeatherInfoEntity;
import com.guicarneirodev.weatherapp.data.mapper.WeatherMapper;
import com.guicarneirodev.weatherapp.data.remote.api.WeatherApi;
import com.guicarneirodev.weatherapp.data.remote.dto.WeatherDataDTO;
import com.guicarneirodev.weatherapp.domain.repository.WeatherRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import retrofit2.Response;

public class WeatherRepositoryImpl implements WeatherRepository {
    private final WeatherApi weatherApi;
    private final WeatherInfoDao weatherInfoDao;
    private final ExecutorService executorService;

    @Inject
    public WeatherRepositoryImpl(WeatherApi weatherApi, WeatherInfoDao weatherInfoDao) {
        this.weatherApi = weatherApi;
        this.weatherInfoDao = weatherInfoDao;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void getWeatherData(String cityName, String userId, Callback<WeatherDataDTO> callback) {
        executorService.execute(() -> {
            try {
                Response<WeatherDataDTO> response = weatherApi.getWeatherData(cityName, userId).execute();
                if (response.isSuccessful() && response.body() != null) {
                    WeatherDataDTO weatherData = response.body();
                    Log.d("WeatherRepository", "Response: " + new Gson().toJson(weatherData)); // Add this line
                    cacheWeatherData(weatherData);
                    new Handler(Looper.getMainLooper()).post(() ->
                            callback.onSuccess(weatherData)
                    );
                } else {
                    WeatherDataDTO cachedData = getCachedWeatherData(cityName);
                    if (cachedData != null) {
                        new Handler(Looper.getMainLooper()).post(() ->
                                callback.onSuccess(cachedData)
                        );
                    } else {
                        new Handler(Looper.getMainLooper()).post(() ->
                                callback.onError("Unable to load weather data")
                        );
                    }
                }
            } catch (IOException e) {
                WeatherDataDTO cachedData = getCachedWeatherData(cityName);
                if (cachedData != null) {
                    new Handler(Looper.getMainLooper()).post(() ->
                            callback.onSuccess(cachedData)
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
    public void getWeatherHistory(String cityName, String userId, Callback<List<WeatherDataDTO>> callback) {
        executorService.execute(() -> {
            try {
                Response<WeatherDataDTO.ListResponseDTO> response =
                        weatherApi.getWeatherHistory(cityName, userId).execute();
                if (response.isSuccessful() && response.body() != null) {
                    new Handler(Looper.getMainLooper()).post(() ->
                            callback.onSuccess(response.body().getData())
                    );
                } else {
                    new Handler(Looper.getMainLooper()).post(() ->
                            callback.onError("Unable to load weather history")
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
