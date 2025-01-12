package com.guicarneirodev.weatherapp.data.remote.api;

import com.guicarneirodev.weatherapp.data.remote.dto.WeatherDataDTO;
import com.guicarneirodev.weatherapp.data.remote.dto.FavoriteCityDTO;
import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;

public interface WeatherApi {
    @POST("/api/weather/cities/{cityName}/users/{userId}")
    Call<WeatherDataDTO> getWeatherData(
            @Path("cityName") String cityName,
            @Path("userId") String userId
    );

    @POST("/api/weather/cities/{cityName}/users/{userId}/history")
    Call<WeatherDataDTO.ListResponseDTO> getWeatherHistory(
            @Path("cityName") String cityName,
            @Path("userId") String userId
    );

    @GET("/api/favorites")
    Call<List<FavoriteCityDTO>> getFavoriteCities(@Query("userId") String userId);

    @POST("/api/favorites")
    Call<FavoriteCityDTO> addFavoriteCity(@Body FavoriteCityDTO city);

    @DELETE("/api/favorites/{id}")
    Call<Void> deleteFavoriteCity(@Path("id") long id);
}
