package com.guicarneirodev.weatherapp.domain.usecase;

import com.guicarneirodev.weatherapp.data.remote.dto.WeatherDataDTO;
import com.guicarneirodev.weatherapp.domain.repository.WeatherRepository;
import javax.inject.Inject;
import java.util.List;

public class GetWeatherHistoryUseCase {
    private final WeatherRepository weatherRepository;

    @Inject
    public GetWeatherHistoryUseCase(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public List<WeatherDataDTO> execute(String cityName, String userId) {
        return weatherRepository.getWeatherHistory(cityName, userId);
    }
}