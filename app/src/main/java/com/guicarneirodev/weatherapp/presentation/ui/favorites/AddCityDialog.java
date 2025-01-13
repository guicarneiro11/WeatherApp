package com.guicarneirodev.weatherapp.presentation.ui.favorites;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.guicarneirodev.weatherapp.databinding.DialogAddCityBinding;

public class AddCityDialog extends DialogFragment {
    private DialogAddCityBinding binding;
    private OnCityAddedListener listener;

    public interface OnCityAddedListener {
        void onCityAdded(String cityName);
    }

    public static AddCityDialog newInstance() {
        return new AddCityDialog();
    }

    public void setOnCityAddedListener(OnCityAddedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogAddCityBinding.inflate(getLayoutInflater());

        return new AlertDialog.Builder(requireContext())
                .setTitle("Add New City")
                .setView(binding.getRoot())
                .setPositiveButton("Add", (dialog, which) -> {
                    String cityName = binding.cityInput.getText().toString().trim();
                    if (!cityName.isEmpty() && listener != null) {
                        listener.onCityAdded(cityName);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
