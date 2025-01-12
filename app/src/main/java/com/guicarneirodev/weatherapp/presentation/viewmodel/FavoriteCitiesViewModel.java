package com.guicarneirodev.weatherapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.guicarneirodev.weatherapp.data.remote.dto.FavoriteCityDTO;
import com.guicarneirodev.weatherapp.domain.usecase.AddFavoriteCityUseCase;
import com.guicarneirodev.weatherapp.domain.usecase.GetFavoriteCitiesUseCase;
import com.guicarneirodev.weatherapp.domain.usecase.RemoveFavoriteCityUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;
import java.util.List;

@HiltViewModel
public class FavoriteCitiesViewModel extends ViewModel {
    private final GetFavoriteCitiesUseCase getFavoriteCitiesUseCase;
    private final AddFavoriteCityUseCase addFavoriteCityUseCase;
    private final RemoveFavoriteCityUseCase removeFavoriteCityUseCase;

    private final MutableLiveData<List<FavoriteCityDTO>> favoriteCities = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public FavoriteCitiesViewModel(GetFavoriteCitiesUseCase getFavoriteCitiesUseCase,
                                   AddFavoriteCityUseCase addFavoriteCityUseCase,
                                   RemoveFavoriteCityUseCase removeFavoriteCityUseCase) {
        this.getFavoriteCitiesUseCase = getFavoriteCitiesUseCase;
        this.addFavoriteCityUseCase = addFavoriteCityUseCase;
        this.removeFavoriteCityUseCase = removeFavoriteCityUseCase;
    }

    public void loadFavoriteCities(String userId) {
        loading.setValue(true);
        try {
            List<FavoriteCityDTO> cities = getFavoriteCitiesUseCase.execute(userId);
            favoriteCities.setValue(cities);
        } catch (Exception e) {
            error.setValue(e.getMessage());
        } finally {
            loading.setValue(false);
        }
    }

    public void addFavoriteCity(FavoriteCityDTO city) {
        loading.setValue(true);
        try {
            FavoriteCityDTO addedCity = addFavoriteCityUseCase.execute(city);
            if (addedCity != null) {
                List<FavoriteCityDTO> currentList = favoriteCities.getValue();
                if (currentList != null) {
                    currentList.add(addedCity);
                    favoriteCities.setValue(currentList);
                }
            } else {
                error.setValue("Failed to add city to favorites");
            }
        } catch (Exception e) {
            error.setValue(e.getMessage());
        } finally {
            loading.setValue(false);
        }
    }

    public void removeFavoriteCity(long cityId) {
        loading.setValue(true);
        try {
            removeFavoriteCityUseCase.execute(cityId);
            List<FavoriteCityDTO> currentList = favoriteCities.getValue();
            if (currentList != null) {
                currentList.removeIf(city -> city.getId() == cityId);
                favoriteCities.setValue(currentList);
            }
        } catch (Exception e) {
            error.setValue(e.getMessage());
        } finally {
            loading.setValue(false);
        }
    }

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
