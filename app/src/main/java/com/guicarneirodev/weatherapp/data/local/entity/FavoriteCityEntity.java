package com.guicarneirodev.weatherapp.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.guicarneirodev.weatherapp.data.local.Converters;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity(tableName = "favorite_cities")
@AllArgsConstructor // Adiciona esta anotação
@NoArgsConstructor  // Mantém o construtor sem argumentos também
public class FavoriteCityEntity {
    @PrimaryKey
    private Long id;
    private String cityName;
    private String userId;
    private LocalDateTime createdAt;
}
