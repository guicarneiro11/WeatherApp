package com.guicarneirodev.weatherapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.guicarneirodev.weatherapp.data.remote.dto.FavoriteCityDTO;
import com.guicarneirodev.weatherapp.domain.repository.FavoriteCityRepository;
import com.guicarneirodev.weatherapp.domain.usecase.AddFavoriteCityUseCase;
import com.guicarneirodev.weatherapp.domain.usecase.GetFavoriteCitiesUseCase;
import com.guicarneirodev.weatherapp.domain.usecase.RemoveFavoriteCityUseCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FavoriteCitiesViewModel extends ViewModel {
    private final GetFavoriteCitiesUseCase getFavoriteCitiesUseCase;
    private final AddFavoriteCityUseCase addFavoriteCityUseCase;
    private final RemoveFavoriteCityUseCase removeFavoriteCityUseCase;

    private final MutableLiveData<List<FavoriteCityDTO>> favoriteCities = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public FavoriteCitiesViewModel(
            GetFavoriteCitiesUseCase getFavoriteCitiesUseCase,
            AddFavoriteCityUseCase addFavoriteCityUseCase,
            RemoveFavoriteCityUseCase removeFavoriteCityUseCase) {
        this.getFavoriteCitiesUseCase = getFavoriteCitiesUseCase;
        this.addFavoriteCityUseCase = addFavoriteCityUseCase;
        this.removeFavoriteCityUseCase = removeFavoriteCityUseCase;
    }

    public void loadFavoriteCities(String userId) {
        loading.setValue(true);
        getFavoriteCitiesUseCase.execute(userId, new FavoriteCityRepository.Callback<List<FavoriteCityDTO>>() {
            @Override
            public void onSuccess(List<FavoriteCityDTO> result) {
                loading.setValue(false);
                favoriteCities.setValue(result);
            }

            @Override
            public void onError(String errorMessage) {
                loading.setValue(false);
                error.setValue(errorMessage);
            }
        });
    }

    public void addFavoriteCity(FavoriteCityDTO city) {
        loading.setValue(true);
        addFavoriteCityUseCase.execute(city, new FavoriteCityRepository.Callback<FavoriteCityDTO>() {
            @Override
            public void onSuccess(FavoriteCityDTO result) {
                loading.setValue(false);
                List<FavoriteCityDTO> currentList = favoriteCities.getValue();
                if (currentList != null) {
                    List<FavoriteCityDTO> newList = new ArrayList<>(currentList);
                    newList.add(result);
                    favoriteCities.setValue(newList);
                } else {
                    favoriteCities.setValue(Collections.singletonList(result));
                }
            }

            @Override
            public void onError(String errorMessage) {
                loading.setValue(false);
                error.setValue(errorMessage);
            }
        });
    }

    public void removeFavoriteCity(long cityId) {
        loading.setValue(true);
        removeFavoriteCityUseCase.execute(cityId, new FavoriteCityRepository.Callback<Void>() {
            @Override
            public void onSuccess(Void result) {
                loading.setValue(false);
                List<FavoriteCityDTO> currentList = favoriteCities.getValue();
                if (currentList != null) {
                    List<FavoriteCityDTO> newList = new ArrayList<>(currentList);
                    newList.removeIf(city -> city.getId() == cityId);
                    favoriteCities.setValue(newList);
                }
            }

            @Override
            public void onError(String errorMessage) {
                loading.setValue(false);
                error.setValue(errorMessage);
            }
        });
    }

    // Getters para LiveData
    public LiveData<List<FavoriteCityDTO>> getFavoriteCities() {
        return favoriteCities;
    }

    public LiveData<String> getError() {
        return error;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }
}