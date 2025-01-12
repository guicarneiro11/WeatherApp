package com.guicarneirodev.weatherapp.data.local.dao;

import androidx.room.*;
import com.guicarneirodev.weatherapp.data.local.entity.FavoriteCityEntity;
import java.util.List;

@Dao
public interface FavoriteCityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(FavoriteCityEntity city);

    @Query("SELECT * FROM favorite_cities WHERE userId = :userId")
    List<FavoriteCityEntity> getFavoriteCities(String userId);

    @Query("SELECT * FROM favorite_cities WHERE id = :id")
    FavoriteCityEntity getFavoriteCity(long id);

    @Delete
    void delete(FavoriteCityEntity city);

    @Query("DELETE FROM favorite_cities WHERE id = :id")
    void deleteById(long id);
}
