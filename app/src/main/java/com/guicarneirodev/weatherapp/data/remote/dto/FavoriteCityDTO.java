package com.guicarneirodev.weatherapp.data.remote.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FavoriteCityDTO {
    private long id;
    private String cityName;
    private String userId;
    private LocalDateTime createdAt;
}
