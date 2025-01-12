package com.guicarneirodev.weatherapp.data.remote.dto;

import java.time.LocalDateTime;
import java.util.List;

public class WeatherDataDTO {
    private Long id;
    private WeatherInfoDTO info;
    private LocalDateTime createdAt;
    private Long favoriteCityId;

    // Getters and Setters

    public static class ListResponseDTO {
        private List<WeatherDataDTO> data;
        private int count;

        // Getters and Setters
    }
}
