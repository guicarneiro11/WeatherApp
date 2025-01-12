package com.guicarneirodev.weatherapp.data.local;

import androidx.room.TypeConverter;
import java.time.LocalDateTime;

import androidx.room.TypeConverter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Converters {
    @TypeConverter
    public static LocalDateTime toLocalDateTime(Long timestamp) {
        return timestamp == null ? null : LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC);
    }

    @TypeConverter
    public static Long fromLocalDateTime(LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.toEpochSecond(ZoneOffset.UTC);
    }
}
