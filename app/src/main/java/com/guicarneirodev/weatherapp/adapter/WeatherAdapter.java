package com.guicarneirodev.weatherapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guicarneirodev.weatherapp.R;
import com.guicarneirodev.weatherapp.model.Weather;
import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    private List<Weather> weatherList = new ArrayList<>();
    private OnWeatherItemClickListener listener;

    public interface OnWeatherItemClickListener {
        void onWeatherItemClick(Weather weather);
    }

    public void setOnWeatherItemClickListener(OnWeatherItemClickListener listener) {
        this.listener = listener;
    }

    static class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView cityName, temperature, description, timestamp;

        WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.cityName);
            temperature = itemView.findViewById(R.id.temperature);
            description = itemView.findViewById(R.id.weather);
            timestamp = itemView.findViewById(R.id.timestamp);
        }
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather_history, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        Weather weather = weatherList.get(position);
        holder.cityName.setText(weather.getCityName());
        holder.temperature.setText(WeatherUtils.formatTemperature(weather.getTemperature()));
        holder.description.setText(weather.getDescription());
        holder.timestamp.setText(weather.getCreatedAt());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onWeatherItemClick(weather);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
        notifyDataSetChanged();
    }
}
