package com.guicarneirodev.weatherapp.data.mapper;

import com.guicarneirodev.weatherapp.data.local.entity.FavoriteCityEntity;
import com.guicarneirodev.weatherapp.data.remote.dto.FavoriteCityDTO;

public class FavoriteCityMapper {

    public static FavoriteCityEntity toEntity(FavoriteCityDTO dto) {
        return new FavoriteCityEntity(
                dto.getId(),
                dto.getCityName(),
                dto.getUserId(),
                dto.getCreatedAt()
        );
    }

    public static FavoriteCityDTO toDto(FavoriteCityEntity entity) {
        FavoriteCityDTO dto = new FavoriteCityDTO();
        dto.setId(entity.getId());
        dto.setCityName(entity.getCityName());
        dto.setUserId(entity.getUserId());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}