package com.guicarneirodev.weatherapp.presenter.details;

import static android.content.Intent.getIntent;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.guicarneirodev.weatherapp.R;
import com.guicarneirodev.weatherapp.adapter.WeatherAdapter;
import com.guicarneirodev.weatherapp.model.Weather;
import com.guicarneirodev.weatherapp.util.WeatherUtils;

import java.util.List;

public class WeatherDetailsActivity extends AppCompatActivity implements WeatherDetailsContract.View {
    private WeatherDetailsPresenter presenter;
    private WeatherAdapter historyAdapter;
    private String cityName;

    private TextView temperatureText;
    private TextView humidityText;
    private TextView windText;
    private LineChart temperatureChart;
    private ImageView cityBackground;
    private ProgressBar loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);

        initViews();
        setupToolbar();
        setupRecyclerView();

        cityName = getIntent().getStringExtra("cityName");
        if (cityName == null) {
            finish();
            return;
        }

        presenter = new WeatherDetailsPresenter(this, this);
        presenter.loadWeatherDetails(cityName);
        presenter.loadWeatherHistory(cityName);

        setupShareButton();
    }

    private void initViews() {
        temperatureText = findViewById(R.id.temperatureText);
        humidityText = findViewById(R.id.humidityText);
        windText = findViewById(R.id.windText);
        temperatureChart = findViewById(R.id.temperatureChart);
        cityBackground = findViewById(R.id.cityBackground);
        loadingView = findViewById(R.id.loadingView);
    }

    private void setupToolbar() {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(cityName);
    }

    private void setupRecyclerView() {
        historyAdapter = new WeatherAdapter();
        // Setup do RecyclerView para histórico, se necessário
    }

    private void setupShareButton() {
        FloatingActionButton shareFab = findViewById(R.id.shareFab);
        shareFab.setOnClickListener(v -> shareWeatherInfo());
    }

    private void shareWeatherInfo() {
        // Implementar compartilhamento
    }

    @Override
    public void showWeatherDetails(Weather weather) {
        temperatureText.setText(WeatherUtils.formatTemperature(weather.getTemperature()));
        humidityText.setText(String.format("Umidade: %d%%", weather.getHumidity()));
        windText.setText(WeatherUtils.formatWindSpeed(weather.getWindSpeed()));

        // Atualizar gráfico e outros elementos visuais
    }

    @Override
    public void showWeatherHistory(List<Weather> historyData) {
        // Atualizar gráfico com dados históricos
        setupTemperatureChart(historyData);
        historyAdapter.setWeatherList(historyData);
    }

    private void setupTemperatureChart(List<Weather> historyData) {
        // Configurar o gráfico com MPAndroidChart
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
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
