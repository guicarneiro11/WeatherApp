package com.guicarneirodev.weatherapp.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weather_info")
public class WeatherInfoEntity {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String cityName;
    private Double temperature;
    private Integer humidity;
    private Double windSpeed;
    private String description;
    private Double feelsLike;
    private Integer pressure;
    private Integer rainProbability;
    private String weatherIcon;

    // Getters
    public Long getId() { return id; }
    public String getCityName() { return cityName; }
    public Double getTemperature() { return temperature; }
    public Integer getHumidity() { return humidity; }
    public Double getWindSpeed() { return windSpeed; }
    public String getDescription() { return description; }
    public Double getFeelsLike() { return feelsLike; }
    public Integer getPressure() { return pressure; }
    public Integer getRainProbability() { return rainProbability; }
    public String getWeatherIcon() { return weatherIcon; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setCityName(String cityName) { this.cityName = cityName; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }
    public void setHumidity(Integer humidity) { this.humidity = humidity; }
    public void setWindSpeed(Double windSpeed) { this.windSpeed = windSpeed; }
    public void setDescription(String description) { this.description = description; }
    public void setFeelsLike(Double feelsLike) { this.feelsLike = feelsLike; }
    public void setPressure(Integer pressure) { this.pressure = pressure; }
    public void setRainProbability(Integer rainProbability) { this.rainProbability = rainProbability; }
    public void setWeatherIcon(String weatherIcon) { this.weatherIcon = weatherIcon; }
}
