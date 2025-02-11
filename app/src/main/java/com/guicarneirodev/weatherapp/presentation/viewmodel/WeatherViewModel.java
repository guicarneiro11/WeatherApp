package com.guicarneirodev.weatherapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.guicarneirodev.weatherapp.data.remote.dto.WeatherDataDTO;
import com.guicarneirodev.weatherapp.domain.repository.WeatherRepository;
import com.guicarneirodev.weatherapp.domain.usecase.GetWeatherHistoryUseCase;
import com.guicarneirodev.weatherapp.domain.usecase.GetWeatherUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {
    private final GetWeatherUseCase getWeatherUseCase;
    private final GetWeatherHistoryUseCase getWeatherHistoryUseCase;

    private final MutableLiveData<WeatherDataDTO> weatherData = new MutableLiveData<>();
    private final MutableLiveData<List<WeatherDataDTO>> weatherHistory = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public WeatherViewModel(GetWeatherUseCase getWeatherUseCase, GetWeatherHistoryUseCase getWeatherHistoryUseCase) {
        this.getWeatherUseCase = getWeatherUseCase;
        this.getWeatherHistoryUseCase = getWeatherHistoryUseCase;
    }

    public void loadWeatherData(String cityName, String userId) {
        loading.setValue(true);
        getWeatherUseCase.execute(cityName, userId, new WeatherRepository.Callback<WeatherDataDTO>() {
            @Override
            public void onSuccess(WeatherDataDTO result) {
                loading.setValue(false);
                weatherData.setValue(result);
            }

            @Override
            public void onError(String errorMessage) {
                loading.setValue(false);
                error.setValue(errorMessage);
            }
        });
    }

    public void loadWeatherHistory(String cityName, String userId) {
        loading.setValue(true);
        try {
            List<WeatherDataDTO> history = getWeatherHistoryUseCase.execute(cityName, userId);
            if (history != null) {
                weatherHistory.setValue(history);
            } else {
                error.setValue("Unable to load weather history");
            }
        } catch (Exception e) {
            error.setValue(e.getMessage());
        } finally {
            loading.setValue(false);
        }
    }

    // Getters para LiveData
    public LiveData<WeatherDataDTO> getWeatherData() {
        return weatherData;
    }

    public LiveData<List<WeatherDataDTO>> getWeatherHistory() {
        return weatherHistory;
    }

    public LiveData<String> getError() {
        return error;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }
}
