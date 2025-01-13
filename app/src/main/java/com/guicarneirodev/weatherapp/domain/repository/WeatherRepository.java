package com.guicarneirodev.weatherapp.domain.repository;

import com.guicarneirodev.weatherapp.data.remote.dto.WeatherDataDTO;

import java.util.List;

public interface WeatherRepository {
    interface Callback<T> {
        void onSuccess(T result);
        void onError(String error);
    }

    void getWeatherData(String cityName, String userId, Callback<WeatherDataDTO> callback);
    void getWeatherHistory(String cityName, String userId, Callback<List<WeatherDataDTO>> callback);

    List<WeatherDataDTO> getWeatherHistory(String cityName, String userId);

    void cacheWeatherData(WeatherDataDTO weatherData);
    WeatherDataDTO getCachedWeatherData(String cityName);
}
