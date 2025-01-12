package com.guicarneirodev.weatherapp.data.mapper;

import com.guicarneirodev.weatherapp.data.local.entity.WeatherInfoEntity;
import com.guicarneirodev.weatherapp.data.remote.dto.WeatherInfoDTO;

public class WeatherMapper {

    public static WeatherInfoEntity toEntity(WeatherInfoDTO dto) {
        WeatherInfoEntity entity = new WeatherInfoEntity();
        entity.setCityName(dto.getCityName());
        entity.setTemperature(dto.getTemperature());
        entity.setHumidity(dto.getHumidity());
        entity.setWindSpeed(dto.getWindSpeed());
        entity.setDescription(dto.getDescription());
        entity.setFeelsLike(dto.getFeelsLike());
        entity.setPressure(dto.getPressure());
        entity.setRainProbability(dto.getRainProbability());
        entity.setWeatherIcon(dto.getWeatherIcon());
        return entity;
    }

    public static WeatherInfoDTO toDto(WeatherInfoEntity entity) {
        WeatherInfoDTO dto = new WeatherInfoDTO();
        dto.setCityName(entity.getCityName());
        dto.setTemperature(entity.getTemperature());
        dto.setHumidity(entity.getHumidity());
        dto.setWindSpeed(entity.getWindSpeed());
        dto.setDescription(entity.getDescription());
        dto.setFeelsLike(entity.getFeelsLike());
        dto.setPressure(entity.getPressure());
        dto.setRainProbability(entity.getRainProbability());
        dto.setWeatherIcon(entity.getWeatherIcon());
        return dto;
    }
}