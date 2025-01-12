package com.guicarneirodev.weatherapp.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(tableName = "weather_info")
@AllArgsConstructor
@NoArgsConstructor
public class WeatherInfoEntity {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String cityName;
    private Double temperature;
    private Integer humidity;
    private Double windSpeed;
    private String description;
    private Double feelsLike;
    private Integer pressure;
    private Integer rainProbability;
    private String weatherIcon;
}
