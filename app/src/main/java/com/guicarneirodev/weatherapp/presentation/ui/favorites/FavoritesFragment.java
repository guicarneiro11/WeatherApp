package com.guicarneirodev.weatherapp.presentation.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.guicarneirodev.weatherapp.data.remote.dto.FavoriteCityDTO;
import com.guicarneirodev.weatherapp.databinding.FragmentFavoritesBinding;
import com.guicarneirodev.weatherapp.presentation.viewmodel.FavoriteCitiesViewModel;

import java.time.LocalDateTime;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavoritesFragment extends Fragment {
    private FragmentFavoritesBinding binding;
    private FavoriteCitiesViewModel viewModel;
    private FavoriteCitiesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(FavoriteCitiesViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void setupViews() {
        binding.addFavoriteButton.setOnClickListener(v -> showAddCityDialog());
    }

    private void showAddCityDialog() {
        AddCityDialog dialog = AddCityDialog.newInstance();
        dialog.setOnCityAddedListener(cityName -> {
            FavoriteCityDTO newCity = new FavoriteCityDTO();
            newCity.setCityName(cityName);
            newCity.setUserId("user123"); // Hardcoded user ID for now
            newCity.setCreatedAt(LocalDateTime.now());

            viewModel.addFavoriteCity(newCity);
        });
        dialog.show(getChildFragmentManager(), "AddCityDialog");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        setupViews();
        observeViewModel();
        loadFavorites();
    }

    private void setupRecyclerView() {
        adapter = new FavoriteCitiesAdapter(city ->
                viewModel.removeFavoriteCity(city.getId())
        );
        binding.favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.favoritesRecyclerView.setAdapter(adapter);
    }

    private void observeViewModel() {
        viewModel.getFavoriteCities().observe(getViewLifecycleOwner(), cities -> {
            adapter.submitList(cities);
        });

        viewModel.getError().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFavorites() {
        viewModel.loadFavoriteCities("user123"); // Hardcoded user ID for now
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
