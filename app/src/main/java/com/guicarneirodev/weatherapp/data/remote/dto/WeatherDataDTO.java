package com.guicarneirodev.weatherapp.data.remote.dto;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class WeatherDataDTO {
    private Long id;
    private WeatherInfoDTO info;

    @SerializedName("createdAt")
    private LocalDateTime createdAt;

    private Long favoriteCityId;

    @Data
    public static class ListResponseDTO {
        @SerializedName("data")
        private List<WeatherDataDTO> data;

        @SerializedName("count")
        private int count;
    }
}