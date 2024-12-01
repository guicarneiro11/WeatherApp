package com.guicarneirodev.weatherapp.presenter.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.guicarneirodev.weatherapp.R;
import com.guicarneirodev.weatherapp.adapter.CitiesAdapter;
import com.guicarneirodev.weatherapp.presenter.add.AddCityActivity;
import com.guicarneirodev.weatherapp.presenter.details.WeatherDetailsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.citiesRecyclerView);
        CitiesAdapter citiesAdapter = new CitiesAdapter();
        recyclerView.setAdapter(citiesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Navegação para AddCity
        FloatingActionButton addFab = findViewById(R.id.addCityFab);
        addFab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddCityActivity.class);
            startActivity(intent);
        });

        // Navegação para Details
        citiesAdapter.setOnItemClickListener(city -> {
            Intent intent = new Intent(MainActivity.this, WeatherDetailsActivity.class);
            intent.putExtra("cityName", city.getName());
            startActivity(intent);
        });
    }
}