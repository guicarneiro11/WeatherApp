package com.guicarneirodev.weatherapp.presenter.details;

import com.guicarneirodev.weatherapp.data.local.SharedPrefsManager;
import com.guicarneirodev.weatherapp.data.remote.WeatherApiClient;
import com.guicarneirodev.weatherapp.model.Weather;
import com.guicarneirodev.weatherapp.presenter.WeatherHistoryResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherDetailsPresenter implements WeatherDetailsContract.Presenter {
    private final WeatherDetailsContract.View view;
    private final WeatherApiClient apiClient;
    private final SharedPrefsManager prefsManager;

    public WeatherDetailsPresenter(WeatherDetailsContract.View view, WeatherDetailsActivity context) {
        this.view = view;
        this.apiClient = new WeatherApiClient();
        this.prefsManager = new SharedPrefsManager(context);
    }

    @Override
    public void loadWeatherDetails(String cityName) {
        view.showLoading();
        String userId = prefsManager.getUserId();

        apiClient.getCurrentWeather(userId).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.showWeatherDetails(response.body());
                } else {
                    view.showError("Erro ao carregar dados do clima");
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                view.hideLoading();
                view.showError(t.getMessage());
            }
        });
    }

    @Override
    public void loadWeatherHistory(String cityName) {
        view.showLoading();
        String userId = prefsManager.getUserId();

        apiClient.getWeatherHistory(cityName, userId).enqueue(new Callback<WeatherHistoryResponse>() {
            @Override
            public void onResponse(Call<WeatherHistoryResponse> call, Response<WeatherHistoryResponse> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.showWeatherHistory(response.body().getData());
                } else {
                    view.showError("Erro ao carregar histórico");
                }
            }

            @Override
            public void onFailure(Call<WeatherHistoryResponse> call, Throwable t) {
                view.hideLoading();
                view.showError(t.getMessage());
            }
        });
    }
}