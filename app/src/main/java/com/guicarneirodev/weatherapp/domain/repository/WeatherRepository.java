package com.guicarneirodev.weatherapp.domain.repository;

import com.guicarneirodev.weatherapp.data.remote.dto.WeatherDataDTO;
import java.util.List;

public interface WeatherRepository {
    WeatherDataDTO getWeatherData(String cityName, String userId);
    List<WeatherDataDTO> getWeatherHistory(String cityName, String userId);
    void cacheWeatherData(WeatherDataDTO weatherData);
    WeatherDataDTO getCachedWeatherData(String cityName);
}
