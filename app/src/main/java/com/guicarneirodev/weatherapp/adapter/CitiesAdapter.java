package com.guicarneirodev.weatherapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guicarneirodev.weatherapp.R;
import com.guicarneirodev.weatherapp.model.City;

import java.util.ArrayList;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityViewHolder> {
    private List<City> cities = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(City city);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {
        TextView cityName, temperature, weather;

        CityViewHolder(@NonNull View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.cityName);
            temperature = itemView.findViewById(R.id.temperature);
            weather = itemView.findViewById(R.id.weather);
        }
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        City city = cities.get(position);
        holder.cityName.setText(city.getName());
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(city);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }
}
