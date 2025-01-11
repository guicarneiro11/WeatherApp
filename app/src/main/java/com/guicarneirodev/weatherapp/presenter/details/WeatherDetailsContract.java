package com.guicarneirodev.weatherapp.presenter.details;

import com.guicarneirodev.weatherapp.model.Weather;

import java.util.List;

public interface WeatherDetailsContract {
    interface View {
        void showWeatherDetails(Weather weather);
        void showWeatherHistory(List<Weather> historyData);
        void showError(String message);
        void showLoading();
        void hideLoading();
    }

    interface Presenter {
        void loadWeatherDetails(String cityName);
        void loadWeatherHistory(String cityName);
    }
}
