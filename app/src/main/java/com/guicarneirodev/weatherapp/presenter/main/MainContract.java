package com.guicarneirodev.weatherapp.presenter.main;

import com.guicarneirodev.weatherapp.model.City;

import java.util.List;

public interface MainContract {
    interface View {
        void showCities(List<City> cities);
        void showLoading();
        void hideLoading();
    }

    interface Presenter {
        void loadCities();
        void onCityClick(City city);
        void addNewCity();
    }
}
