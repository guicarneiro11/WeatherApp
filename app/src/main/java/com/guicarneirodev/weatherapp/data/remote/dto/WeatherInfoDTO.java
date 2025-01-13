package com.guicarneirodev.weatherapp.data.remote.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class WeatherInfoDTO {
    private String cityName;
    private Double temperature;
    private Integer humidity;
    private Double windSpeed;
    private String description;

    @SerializedName("feels_like")
    private Double feelsLike;

    private Integer pressure;

    @SerializedName("rain_probability")
    private Integer rainProbability;

    @SerializedName("weather_icon")
    private String weatherIcon;
}
