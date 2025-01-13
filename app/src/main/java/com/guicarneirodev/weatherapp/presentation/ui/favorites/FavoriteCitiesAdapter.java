package com.guicarneirodev.weatherapp.presentation.ui.favorites;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.guicarneirodev.weatherapp.data.remote.dto.FavoriteCityDTO;
import com.guicarneirodev.weatherapp.databinding.ItemFavoriteCityBinding;

public class FavoriteCitiesAdapter extends ListAdapter<FavoriteCityDTO, FavoriteCitiesAdapter.ViewHolder> {
    private final OnCityClickListener listener;

    public interface OnCityClickListener {
        void onCityClick(FavoriteCityDTO city);
        void onRemoveCity(FavoriteCityDTO city);
    }

    protected FavoriteCitiesAdapter(OnCityClickListener listener) {
        super(new CityDiffCallback());
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFavoriteCityBinding binding = ItemFavoriteCityBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavoriteCityDTO city = getItem(position);
        holder.bind(city);
        holder.itemView.setOnClickListener(v -> listener.onCityClick(city));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemFavoriteCityBinding binding;

        ViewHolder(ItemFavoriteCityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(FavoriteCityDTO city) {
            binding.cityName.setText(city.getCityName());
            binding.removeButton.setOnClickListener(v -> listener.onRemoveCity(city));
        }
    }

    static class CityDiffCallback extends DiffUtil.ItemCallback<FavoriteCityDTO> {
        @Override
        public boolean areItemsTheSame(@NonNull FavoriteCityDTO oldItem, @NonNull FavoriteCityDTO newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull FavoriteCityDTO oldItem, @NonNull FavoriteCityDTO newItem) {
            return oldItem.getCityName().equals(newItem.getCityName());
        }
    }
}