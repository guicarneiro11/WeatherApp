package com.guicarneirodev.weatherapp.presenter.add;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.guicarneirodev.weatherapp.R;
import com.guicarneirodev.weatherapp.adapter.CitiesAdapter;
import com.guicarneirodev.weatherapp.model.City;

import java.util.List;

public class AddCityActivity extends AppCompatActivity implements AddCityContract.View {
    private AddCityPresenter presenter;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private CitiesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        initViews();
        setupToolbar();
        setupRecyclerView();
        setupSearchView();

        presenter = new AddCityPresenter(this, this);
    }

    private void initViews() {
        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.searchResultsRecyclerView);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setupToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.add_city);
    }

    private void setupRecyclerView() {
        adapter = new CitiesAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnItemClickListener(city -> presenter.addCity(city));
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.searchCities(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() >= 3) {
                    presenter.searchCities(newText);
                }
                return true;
            }
        });
    }

    @Override
    public void showSearchResults(List<City> cities) {
        adapter.setCities(cities);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCityAdded(City city) {
        Toast.makeText(this, getString(R.string.city_added_successfully), Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
