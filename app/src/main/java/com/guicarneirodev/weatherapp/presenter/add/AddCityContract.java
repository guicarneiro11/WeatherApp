package com.guicarneirodev.weatherapp.presenter.add;

import com.guicarneirodev.weatherapp.model.City;

import java.util.List;

public interface AddCityContract {
    interface View {
        void showSearchResults(List<City> cities);
        void showLoading();
        void hideLoading();
        void showError(String message);
        void onCityAdded(City city);
    }

    interface Presenter {
        void searchCities(String query);
        void addCity(City city);
        void validateCity(String cityName);
    }
}
