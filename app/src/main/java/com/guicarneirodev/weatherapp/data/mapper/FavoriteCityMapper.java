package com.guicarneirodev.weatherapp.data.mapper;

import com.guicarneirodev.weatherapp.data.local.entity.FavoriteCityEntity;
import com.guicarneirodev.weatherapp.data.remote.dto.FavoriteCityDTO;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class FavoriteCityMapper {

    public static FavoriteCityEntity toEntity(FavoriteCityDTO dto) {
        FavoriteCityEntity entity = new FavoriteCityEntity();
        entity.setId(dto.getId());
        entity.setCityName(dto.getCityName());
        entity.setUserId(dto.getUserId());
        entity.setCreatedAt(dto.getCreatedAt() != null ?
                dto.getCreatedAt().toEpochSecond(ZoneOffset.UTC) : null);
        return entity;
    }

    public static FavoriteCityDTO toDto(FavoriteCityEntity entity) {
        FavoriteCityDTO dto = new FavoriteCityDTO();
        dto.setId(entity.getId());
        dto.setCityName(entity.getCityName());
        dto.setUserId(entity.getUserId());
        dto.setCreatedAt(entity.getCreatedAt() != null ?
                LocalDateTime.ofEpochSecond(entity.getCreatedAt(), 0, ZoneOffset.UTC) : null);
        return dto;
    }
}