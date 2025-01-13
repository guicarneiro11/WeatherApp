package com.guicarneirodev.weatherapp.domain.usecase;

import com.guicarneirodev.weatherapp.data.remote.dto.WeatherDataDTO;
import com.guicarneirodev.weatherapp.domain.repository.WeatherRepository;

import javax.inject.Inject;

public class GetWeatherUseCase {
    private final WeatherRepository weatherRepository;

    @Inject
    public GetWeatherUseCase(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public void execute(String cityName, String userId, WeatherRepository.Callback<WeatherDataDTO> callback) {
        weatherRepository.getWeatherData(cityName, userId, callback);
    }
}
