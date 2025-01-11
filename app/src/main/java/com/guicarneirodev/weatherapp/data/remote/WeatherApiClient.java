package com.guicarneirodev.weatherapp.data.remote;

import com.guicarneirodev.weatherapp.model.Weather;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class WeatherApiClient {
    private static final String BASE_URL = "sua_base_url";
    private final WeatherApiService apiService;

    public WeatherApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(WeatherApiService.class);
    }

    public interface WeatherApiService {
        @GET("api/weather/users/{userId}")
        Call<Weather> getCurrentWeather(@Path("userId") String userId);

        @GET("api/weather/cities/{cityName}/users/{userId}/history")
        Call<WeatherHistoryResponse> getWeatherHistory(
                @Path("cityName") String cityName,
                @Path("userId") String userId
        );
    }
}
