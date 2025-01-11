package com.guicarneirodev.weatherapp.presenter.add;

import android.content.Context;

import com.guicarneirodev.weatherapp.data.local.SharedPrefsManager;
import com.guicarneirodev.weatherapp.data.remote.WeatherApiClient;
import com.guicarneirodev.weatherapp.model.City;

import retrofit2.Call;
import retrofit2.Response;

public class AddCityPresenter implements AddCityContract.Presenter {
    private final AddCityContract.View view;
    private final WeatherApiClient apiClient;
    private final SharedPrefsManager prefsManager;

    public AddCityPresenter(AddCityContract.View view, Context context) {
        this.view = view;
        this.apiClient = new WeatherApiClient();
        this.prefsManager = new SharedPrefsManager(context);
    }

    @Override
    public void searchCities(String query) {
        if (query == null || query.trim().isEmpty()) {
            return;
        }

        view.showLoading();
        validateCity(query);
    }

    @Override
    public void validateCity(String cityName) {
        String userId = prefsManager.getUserId();
        apiClient.validateCity(cityName).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                view.hideLoading();
                if (response.isSuccessful()) {
                    City city = new City();
                    city.setName(cityName);
                    view.onCityAdded(city);
                } else {
                    view.showError("Cidade não encontrada");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                view.hideLoading();
                view.showError("Erro ao validar cidade: " + t.getMessage());
            }
        });
    }

    @Override
    public void addCity(City city) {
        String userId = prefsManager.getUserId();
        FavoriteCityDTO favoriteCityDTO = new FavoriteCityDTO();
        favoriteCityDTO.setCityName(city.getName());
        favoriteCityDTO.setUserId(userId);

        apiClient.addFavoriteCity(favoriteCityDTO).enqueue(new Callback<FavoriteCityDTO>() {
            @Override
            public void onResponse(Call<FavoriteCityDTO> call, Response<FavoriteCityDTO> response) {
                if (response.isSuccessful()) {
                    view.onCityAdded(city);
                } else {
                    view.showError("Erro ao adicionar cidade");
                }
            }

            @Override
            public void onFailure(Call<FavoriteCityDTO> call, Throwable t) {
                view.showError("Erro ao adicionar cidade: " + t.getMessage());
            }
        });
    }
}
