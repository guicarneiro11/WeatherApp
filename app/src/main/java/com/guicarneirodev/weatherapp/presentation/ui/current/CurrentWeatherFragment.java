package com.guicarneirodev.weatherapp.presentation.ui.current;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.guicarneirodev.weatherapp.R;
import com.guicarneirodev.weatherapp.databinding.FragmentCurrentWeatherBinding;
import com.guicarneirodev.weatherapp.presentation.viewmodel.WeatherViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CurrentWeatherFragment extends Fragment {
    private FragmentCurrentWeatherBinding binding;
    private WeatherViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCurrentWeatherBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
        observeViewModel();
    }

    private void setupViews() {
        binding.cityInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String city = binding.cityInput.getText().toString().trim();
                if (!city.isEmpty()) {
                    viewModel.loadWeatherData(city, "user123"); // Hardcoded user ID for now
                    return true;
                }
            }
            return false;
        });

        binding.swipeRefresh.setOnRefreshListener(() -> {
            String city = binding.cityInput.getText().toString().trim();
            if (!city.isEmpty()) {
                viewModel.loadWeatherData(city, "user123");
            }
        });
    }

    private void observeViewModel() {
        viewModel.getWeatherData().observe(getViewLifecycleOwner(), weatherData -> {
            if (weatherData != null && weatherData.getInfo() != null) {
                binding.temperatureText.setText(String.format("%.1f°C", weatherData.getInfo().getTemperature()));
                binding.descriptionText.setText(weatherData.getInfo().getDescription());
                binding.humidityText.setText(String.format("Humidity: %d%%", weatherData.getInfo().getHumidity()));
                binding.windSpeedText.setText(String.format("Wind: %.1f km/h", weatherData.getInfo().getWindSpeed()));
                binding.pressureText.setText(String.format("Pressure: %d hPa", weatherData.getInfo().getPressure()));
            }
        });

        viewModel.getError().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getLoading().observe(getViewLifecycleOwner(), loading -> {
            binding.swipeRefresh.setRefreshing(loading);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
