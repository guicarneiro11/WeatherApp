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

    public WeatherDataDTO execute(String cityName, String userId) {
        return weatherRepository.getWeatherData(cityName, userId);
    }
}
