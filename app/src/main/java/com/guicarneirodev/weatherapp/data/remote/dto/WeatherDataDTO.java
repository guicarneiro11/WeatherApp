package com.guicarneirodev.weatherapp.data.remote.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.Getter;

@Data
public class WeatherDataDTO {
    private Long id;
    private WeatherInfoDTO info;
    private LocalDateTime createdAt;
    private Long favoriteCityId;

    @Data
    public static class ListResponseDTO {
        @Getter
        private List<WeatherDataDTO> data;
        private int count;

    }
}